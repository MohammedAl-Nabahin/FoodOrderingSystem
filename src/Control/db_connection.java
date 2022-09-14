/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mohdo
 */
public class db_connection {

    public static Connection conn = null;
    public static String url = "jdbc:mysql://127.0.0.1:3306/fos";
    public static String username = "root";
    public static String password = "";

    public static void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connection OK");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(db_connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(db_connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
