/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.ConnexionDB;
import Entity.Admin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author NacimGastli
 */
public class Service_Admin implements CRUD<Admin> {
     private Statement ste;
    private PreparedStatement pst, pst2;
    private ResultSet rs, rs2;
    private ConnexionDB con;

    public Service_Admin() {
         con = ConnexionDB.getInstance();
    }

    @Override
    public int insert(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Admin t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int SingIn(Admin p) {
         try {
             String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLEs= ? and (EMAIL = ? OR USERNAME = ?) ";
             
             pst = ConnexionDB.getCnx().prepareStatement(requete);
             pst.setString(1, p.getPassword());
             pst.setInt(2, 0);
             pst.setString(3, p.getEmail());
             pst.setString(4, p.getUsername());
             
             rs = pst.executeQuery();
             
             boolean v = rs.next();
             
             if (v == true) 
                     return 3;
                 
             else 
                 return 0;
                 
             
          
         } catch (SQLException ex) {
             Logger.getLogger(Service_Admin.class.getName()).log(Level.SEVERE, null, ex);
         }
         return 0;
 }

    @Override
    public int VerifierCompte(Admin t, String code) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Admin chercher(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



    @Override
    public List<Admin> getshow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMd5(String mdp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
