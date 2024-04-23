/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package external;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author T0322864
 */
//The function which will be used for whole REST API because connects to DB
public class SqLiteConnection {
    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            //PATH for the database, change it if you can connect to DB.
            String url = "jdbc:sqlite:C:\\Users\\Артем\\Documents\\NetBeansProjects\\REST API Database_DB\\TravelBuddy.db";
            System.out.print(DriverManager.getConnection(url) + "success");
            return DriverManager.getConnection(url);            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error with connection to DB", e);
        }
    }
    
}
