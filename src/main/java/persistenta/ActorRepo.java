package persistenta;

import model.Administrator;
import model.Farmacist;
import model.GenericActor;
import model.Sectie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ActorRepo {
    private HashMap<String, GenericActor> elems;
    private SessionFactory sessionFactory;

    public ActorRepo(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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

    @Transactional
    public List<GenericActor> getAllNonAdmin() {
        List nonAdminList = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            nonAdminList = session.createCriteria(Sectie.class).list();
            nonAdminList.addAll(session.createCriteria(Farmacist.class).list());
            session.getTransaction().commit();
            session.close();
        }
        return nonAdminList;
    }

    @Transactional
    public GenericActor getOne(String username) {
        GenericActor genericActor = null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            genericActor = session.get(GenericActor.class, username);
            session.getTransaction().commit();
            session.close();
        }
        return genericActor;
    }
}