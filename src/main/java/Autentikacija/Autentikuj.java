package Autentikacija;

import BazaPodataka.DBKonekcija;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Autentikuj {
    DBKonekcija connection = new DBKonekcija();
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public boolean authenticate(String userName, String password) {
        boolean doesUserExist = false;
        con = connection.geConnection();
        try {
            pst = con.prepareStatement("select * from korisnik where korisnicko_ime=? and sifra=?");
            pst.setString(1, userName);
            pst.setString(2, password);
            rs = pst.executeQuery();
            if (rs.next()) {
                doesUserExist = true;
            }
            rs.close();
            pst.close();
            con.close();
            connection.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Autentikuj.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doesUserExist;
    }
}
