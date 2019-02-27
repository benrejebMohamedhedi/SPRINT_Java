/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Database.ConnexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author NacimGastli
 */
public class Admin extends User{

    public Admin(String email, String password) {
        super(email, password);
    }

    public Admin(int id, String prenom, String nom) {
        super(id, prenom, nom);
    }
    
    
    public Admin Session(Admin p) throws SQLException {
        Statement ste;
        PreparedStatement pst;
        ResultSet rs;
        ConnexionDB con ;
        con = ConnexionDB.getInstance();
        String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLES= ? and (EMAIL = ? OR USERNAME = ?) ";

        pst = ConnexionDB.getCnx().prepareStatement(requete);
        pst.setString(1, p.getPassword());
        pst.setInt(2, 0);
        pst.setString(3, p.getEmail());
        pst.setString(4, p.getUsername());
        rs = pst.executeQuery();
        
        while (rs.next()) {
            
            Admin R = new Admin( rs.getInt("id"),rs.getString("prenom"), rs.getString("nom"));
            R.setNom(rs.getString("nom"));
            R.setPrenom(rs.getString("prenom"));
            R.setid(rs.getInt("id"));
          
            return R;
        }
        return null;

    }

    @Override
    public String toString() {
        return "Admin{" + '}';
    }
    
}
