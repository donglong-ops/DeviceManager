/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.DBUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author donglong
 */
public class Utils implements Serializable{

    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url = "jdbc:sqlserver://localhost:1433;databaseName=Ass4;user=sa;password=123456";

    public static Connection openConnect() throws Exception{
      
        try {
            Class.forName(driver);
            Connection c = DriverManager.getConnection(url);
            return c;
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Cannot connect to database (" + e + ")");
        }
        return null;
    }

}
