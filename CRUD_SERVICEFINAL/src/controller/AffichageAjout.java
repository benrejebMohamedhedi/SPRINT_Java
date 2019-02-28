
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entities.service;
import connexion.conDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import entities.prestataire;
/**
 *
 * @author karim
 */
public class AffichageAjout {
   
static Connection cnx;
    
    public AffichageAjout() throws SQLException {
       cnx = conDB.getInstance().getCnx();
    }    
    
    public ObservableList<service> afficherService() throws SQLException
      {
          
         ObservableList<service> listService = FXCollections.observableArrayList() ;
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM service";
        ResultSet resultat = stm.executeQuery(req);  
         while(resultat.next())
        {
        int id_service                =   resultat.getInt("id_service");
            String catgorie                 =   resultat.getString("categorie");
            String sous_categorie        =   resultat.getString("sous_categorie");
            String image =resultat.getString("image");
            listService.add(new service(id_service,catgorie,sous_categorie,image)); 
            
        } 
          
        return listService ; 
        
      }   
    
     public ObservableList<prestataire> afficherPrestataire(int id ) throws SQLException
      {
          
         ObservableList<prestataire> listServicePrestataire = FXCollections.observableArrayList() ;
        Statement stm = cnx.createStatement();
        String req = "select service.id_service,fos_user.nom , fos_user.prenom ,fos_user.photo_profil from fos_user,service where fos_user.specialite=service.sous_categorie and service.id_service='"+id+"'   ";
        ResultSet resultat = stm.executeQuery(req);  
         while(resultat.next())
        {
        int id_service                =   resultat.getInt("id_service");
            String nom                 =   resultat.getString("nom");
            String prenom        =   resultat.getString("prenom");
            String  photo_profil =resultat.getString("photo_profil");
            listServicePrestataire.add(new prestataire(id_service,nom, prenom,photo_profil)) ;
            
        } 
          
        return   listServicePrestataire ; 
        
      }    
    
    
   
    
    
    
    
  

       

    
}
