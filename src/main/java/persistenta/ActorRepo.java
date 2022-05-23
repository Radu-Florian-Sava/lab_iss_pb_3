package persistenta;

import iss.ubbcluj.ro.interfataisspb3.MainApplication;
import model.Administrator;
import model.Farmacist;
import model.GenericActor;
import model.Sectie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

public class ActorRepo {
    private HashMap<String, GenericActor> elems;
    private JdbcUtils dbUtils;
    private SessionFactory sessionFactory;

    public ActorRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.elems = new HashMap<>();
        try {
            Properties props = new Properties();
            props.load(MainApplication.class.getResourceAsStream("db.properties"));
            dbUtils = new JdbcUtils(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public boolean addMedic(String username, String password, String checkPassword) {
        if (!Objects.equals(checkPassword, password)) {
            return false;
        }
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new Sectie(username, password));
            session.getTransaction().commit();
            session.close();
        }
        return true;
    }

    @Transactional
    public boolean addFarmacist(String username, String password, String checkPassword) {
        if (!Objects.equals(checkPassword, password)) {
            return false;
        }
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new Farmacist(username, password));
            session.getTransaction().commit();
            session.close();
        }
        return true;
    }

    @Transactional
    public boolean loginAdmin(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Administrator administrator = session.get(Administrator.class, username);
            if (elems.containsKey(username)) {
                return false;
            } else {
                elems.put(username, administrator);
            }
            session.getTransaction().commit();
        }
        return elems.get(username).getParola().equals(password);
    }

    @Transactional
    public boolean loginMedic(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Sectie sectie = session.get(Sectie.class, username);
            if (elems.containsKey(username)) {
                return false;
            } else {
                elems.put(username, sectie);
            }
            session.getTransaction().commit();
        }
        return elems.get(username).getParola().equals(password);
    }

    @Transactional
    public boolean loginFarmacist(String username, String password) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Farmacist farmacist = session.get(Farmacist.class, username);
            if (elems.containsKey(username)) {
                return false;
            } else {
                elems.put(username, farmacist);
            }
            session.getTransaction().commit();
        }
        return elems.get(username).getParola().equals(password);
    }
}