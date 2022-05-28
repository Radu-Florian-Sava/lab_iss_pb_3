package persistenta;

import iss.ubbcluj.ro.interfataisspb3.MainApplication;
import model.Reteta;
import model.RetetaId;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;

public class RetetaRepo {
    private SessionFactory sessionFactory;
    private JdbcUtils dbUtils;
    private LocalDateTime lastDateTime;

    public RetetaRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        try {
            Properties props = new Properties();
            props.load(MainApplication.class.getResourceAsStream("db.properties"));
            dbUtils = new JdbcUtils(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void addReteta(Reteta reteta) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            lastDateTime = ((RetetaId) session.save(reteta)).getData();
            session.getTransaction().commit();
            session.close();
        }
    }

    @Transactional
    public void finishCommand(RetetaId retetaId) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            SQLQuery sqlQuery = session.createSQLQuery("UPDATE retete SET onorata = true WHERE data=? AND numeSectie=?;");
            sqlQuery.setParameter(0, retetaId.getData());
            sqlQuery.setParameter(1, retetaId.getNumeSectie());
            sqlQuery.executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    @Transactional
    public List<Reteta> getAll() {
        List retetaList = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            retetaList = session.createCriteria(Reteta.class).list();
            session.getTransaction().commit();
            session.close();
        }
        return retetaList;
    }

    @Transactional
    public List<Reteta> getAllNeonorate() {
        List retetaList = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            retetaList = session.createQuery("FROM Reteta R WHERE R.onorata = false").list();
            session.getTransaction().commit();
            session.close();
        }
        return retetaList;
    }

}
