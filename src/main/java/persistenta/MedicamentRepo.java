package persistenta;

import iss.ubbcluj.ro.interfataisspb3.MainApplication;
import model.Medicament;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class MedicamentRepo {
    private JdbcUtils dbUtils;

    public MedicamentRepo() {
        try {
            Properties props = new Properties();
            props.load(MainApplication.class.getResourceAsStream("db.properties"));
            dbUtils = new JdbcUtils(props);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
