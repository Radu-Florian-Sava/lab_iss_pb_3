package persistenta;

import iss.ubbcluj.ro.interfataisspb3.MainApplication;
import model.Medicament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class MedicamentRepo {
    private SessionFactory sessionFactory;
    private JdbcUtils dbUtils;

    public MedicamentRepo(SessionFactory sessionFactory) {
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
    public void addMedicament(Medicament medicament) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(medicament);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Transactional
    public void toggleMedicament(String denumire) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Medicament medicament = session.get(Medicament.class, denumire);
            medicament.setUtilizabil(!medicament.isUtilizabil());
            session.update(medicament);
            session.getTransaction().commit();
            session.close();
        }
    }

    @Transactional
    public List<Medicament> getAll() {
        List medicamentList = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            medicamentList = session.createCriteria(Medicament.class).list();
            session.getTransaction().commit();
            session.close();
        }
        return medicamentList;
    }

    @Transactional
    public List<Medicament> getAllVisible() {
        List medicamentList = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            medicamentList = session.createQuery("FROM Medicament M WHERE M.utilizabil = true").list();
            session.getTransaction().commit();
            session.close();
        }
        return medicamentList;
    }

    @Transactional
    public Medicament getOne(String denumire) {
        Medicament medicament = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            medicament = session.get(Medicament.class, denumire);
            session.getTransaction().commit();
            session.close();
        }
        return medicament;
    }
}
