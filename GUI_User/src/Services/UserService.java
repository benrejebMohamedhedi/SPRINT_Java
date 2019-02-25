/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.ConnexionDB;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NacimGastli
 */
public class UserService {
     Connection cnx;
public UserService()throws SQLException {
       cnx = ConnexionDB.getInstance().getCnx();
    
}
    public void ajouterAlaBase2(User p) {
         try {
             p.getNom();
             String req = "INSERT INTO `USER`( `nom`, `prenom`) VALUES (?,?)";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setString(1, p.getNom());
             pstm.setString(2, p.getPrenom());
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    public ArrayList<User> getAllUsers() throws SQLException {
       ArrayList<User> retour = new ArrayList<>();
       Statement stm = cnx.createStatement();
        String req = "SELECT * FROM USER";
        ResultSet resultat = stm.executeQuery(req);
        while(resultat.next()){
           int id= resultat.getInt(1);
            String nom = resultat.getString("nom");
           String prenom= resultat.getString("prenom");
           retour.add(new User(id, nom, prenom));
            
        }
            
        return retour;
    }
    public void DeleteUser(User p) {
         try {
             
             String req;
             req = "DELETE FROM personne where id=?";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, p.getid());
             pstm.executeUpdate();
         } catch (SQLException ex) {
             Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
}
