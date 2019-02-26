/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceLigneCom;

import DB.MyDBcon;
import Entities.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author nizar
 */
public class Ligne_ComService {
  Connection cnx;
    public Ligne_ComService() throws SQLException {
        cnx = MyDBcon.getInstance().getCnx();
    }

    private Ligne_ComService(int quantite) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
public ObservableList<Integer> getAllIds() throws SQLException
{
    ObservableList<Integer> li =FXCollections.observableArrayList();
    Statement stm = cnx.createStatement();
        String req = "SELECT `id_produit` FROM ligne_commande";
        ResultSet resultat = stm.executeQuery(req);
         while(resultat.next()){
           int id= resultat.getInt(1);
           li.add(id);
         }
         return li;
         
}
       public void ajouterLigne(int  p,int id_panier) {
        try {

            String req = "INSERT INTO `ligne_commande`(`id_commande`,`id_produit`) VALUES ('"+id_panier+"','"+p+"')";
            Statement pstm = cnx.createStatement();
            pstm.executeUpdate(req);
             System.out.println("Insertion ligne done");
        }catch (SQLException ex) {
            Logger.getLogger(Ligne_ComService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } public void supprimerLigne(int id){
        try {
            PreparedStatement pt=cnx.prepareStatement("delete from ligne_commande where id_commande IN(select id_commande from commande where id_user=? and etat=0)");
            pt.setInt(1,id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ligne_ComService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    public int  getquantite(int id_p) throws SQLException
    {
        int q=0;
         
        PreparedStatement pt=cnx.prepareStatement( "SELECT quantite FROM `ligne_commande`  where `id_produit`=?");
        
        pt.setInt(1, id_p);
        ResultSet resultat = pt.executeQuery();
        while(resultat.next()){
            q= resultat.getInt("quantite");
           
           
            
        }
            
        return q;
        
        
    }
   public void modifierQuantite(int q,Produit p){
        try {
            PreparedStatement pt=cnx.prepareStatement("update ligne_commande set quantite=? where id_produit=?");
            pt.setInt(1,q);
            pt.setInt(2,p.getId_produit());
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Ligne_ComService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      ArrayList<Ligne_ComService> getAllLignes() throws SQLException {
       ArrayList<Ligne_ComService> retour = new ArrayList<>();
       Statement stm = cnx.createStatement();
        String req = "SELECT *  FROM `ligne_commande`";
        ResultSet resultat = stm.executeQuery(req);
        while(resultat.next()){
           int quantite= resultat.getInt("quantite");
            int id_prod= resultat.getInt("id_produit");
           int id_commande= resultat.getInt("id_commande");
           retour.add(new Ligne_ComService(quantite));
            
        }
            
        return retour;
    }
}
