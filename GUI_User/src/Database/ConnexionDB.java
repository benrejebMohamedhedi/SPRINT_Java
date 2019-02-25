/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NacimGastli
 */
public class ConnexionDB {
    private static ConnexionDB instance;
     private static Connection cnx;
    private static ConnexionDB con;   
    final String url = "jdbc:mysql://127.0.0.1/esprit";
    final String login = "root";
    final String password = "root";
  

  private ConnexionDB() {
           
        try {
            cnx =DriverManager.getConnection(url, login, password);
            System.out.println("connected");
            
// TODO code application logic here
        } catch (SQLException ex) {
            Logger.getLogger(ConnexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static ConnexionDB getInstance(){
        if(con==null)
        {
            con = new ConnexionDB();
        }
        return con;
    }
   
    
    public static void main(String[] args) {
     ConnexionDB con= new ConnexionDB();       
    }

    public static Connection getCnx() {
        return cnx;
    }
}
