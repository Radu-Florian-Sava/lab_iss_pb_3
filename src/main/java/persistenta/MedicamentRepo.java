package persistenta;

import iss.ubbcluj.ro.interfataisspb3.MainApplication;
import model.Medicament;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Properties;

public class MedicamentRepo {
    private  SessionFactory sessionFactory;
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
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(medicament);
            session.getTransaction().commit();
            session.close();
        }

    }


}
