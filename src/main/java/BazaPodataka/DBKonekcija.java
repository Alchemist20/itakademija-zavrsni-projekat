package BazaPodataka;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBKonekcija {
    public Connection con;

    public Connection geConnection() {
        String url = "jdbc:postgresql://localhost:5432/my_final_project?serverTimezone=UTC";
        String username = "postgres";
        String password = "seadragon";
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            Logger.getLogger(DBKonekcija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
