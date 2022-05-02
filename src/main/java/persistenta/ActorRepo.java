package persistenta;

import iss.ubbcluj.ro.interfataisspb3.MainApplication;
import model.Administrator;
import model.Farmacist;
import model.GenericActor;
import model.Sectie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class ActorRepo {
    private HashMap<String, GenericActor> elems;
    private JdbcUtils dbUtils;

    public ActorRepo() {
        this.elems = new HashMap<>();
        try {
            Properties props = new Properties();
            props.load(MainApplication.class.getResourceAsStream("db.properties"));
            dbUtils = new JdbcUtils(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loginAdmin(String username, String password) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStm = con.prepareStatement("select * from GenericActors where username=? AND password=? AND userType=0;")) {
            preStm.setString(1, username);
            preStm.setString(2, password);
            ResultSet result = preStm.executeQuery();
            result.next();
            String foundUsername = result.getString("username");
            String foundPassword = result.getString("password");
            Administrator administrator = new Administrator(foundUsername, foundPassword);
            result.close();
            if (elems.containsKey(foundUsername)) {
                return false;
            } else {
                elems.put(username, administrator);
            }
        } catch (SQLException ex) {
            System.err.println("Error BD" + ex);
        }
        if (!elems.containsKey(username)) {
            return false;
        }
        return elems.get(username).getParola().equals(password);
    }

    public boolean loginMedic(String username, String password) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStm = con.prepareStatement("select * from GenericActors where username=? AND password=? AND userType=1;")) {
            preStm.setString(1, username);
            preStm.setString(2, password);
            ResultSet result = preStm.executeQuery();
            result.next();
            String foundUsername = result.getString("username");
            String foundPassword = result.getString("password");
            Sectie sectie = new Sectie(foundUsername, foundPassword);
            result.close();
            if (elems.containsKey(foundUsername)) {
                return false;
            } else {
                elems.put(username, sectie);
            }
        } catch (SQLException ex) {
            System.err.println("Error BD" + ex);
        }
        if (!elems.containsKey(username)) {
            return false;
        }
        return elems.get(username).getParola().equals(password);
    }

    public boolean loginFarmacist(String username, String password) {
        Connection con = dbUtils.getConnection();
        try (PreparedStatement preStm = con.prepareStatement("select * from GenericActors where username=? AND password=? AND userType=2;")) {
            preStm.setString(1, username);
            preStm.setString(2, password);
            ResultSet result = preStm.executeQuery();
            result.next();
            String foundUsername = result.getString("username");
            String foundPassword = result.getString("password");
            Farmacist farmacist = new Farmacist(foundUsername, foundPassword);
            result.close();
            if (elems.containsKey(foundUsername)) {
                return false;
            } else {
                elems.put(username, farmacist);
            }
        } catch (SQLException ex) {
            System.err.println("Error BD" + ex);
        }
        if (!elems.containsKey(username)) {
            return false;
        }
        return elems.get(username).getParola().equals(password);
    }
}