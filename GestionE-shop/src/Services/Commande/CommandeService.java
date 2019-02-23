 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Commande;

import DB.MyDBcon;
import Entities.Commande;
import Entities.Produit;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static org.apache.xalan.lib.ExsltDatetime.date;
import static org.apache.xalan.lib.ExsltDatetime.date;
import static org.apache.xalan.lib.ExsltDatetime.date;
import static org.apache.xalan.lib.ExsltDatetime.date;
import static org.apache.xalan.lib.ExsltDatetime.date;
import serviceLigneCom.Ligne_ComService;

/**
 *
 * @author nizar
 */
public class CommandeService {
    Connection cnx;
    Ligne_ComService lc =new Ligne_ComService();

    public CommandeService() throws SQLException {
               cnx = MyDBcon.getInstance().getCnx();
 
    }
    public boolean existePanier(int id) throws SQLException {
        int count = 0;
        String req = "select count(*) from `commande` where id_user=? and etat=?";
        PreparedStatement pstm = cnx.prepareStatement(req);
        pstm.setInt(1, id);
        pstm.setInt(2, 0);
        ResultSet res = pstm.executeQuery();
        while (res.next()) {
            count = res.getInt(1);
        }
        if (count == 0) {
            return false;
        }
        return true;
    }
   public ObservableList<Integer> getIdpanier() throws SQLException
   {
           ObservableList<Integer> items =FXCollections.observableArrayList();
         String r = "select id_commande from `commande` where id_user=?";
            PreparedStatement pst = cnx.prepareStatement(r);
            pst.setInt(1, 1);
            ResultSet res = pst.executeQuery();
            
            while (res.next()) {

              items.add(res.getInt("id_commande"));

            }
            return  items;
   }
     public void ajouterAuPanier(Commande p, Produit prod, int id) throws SQLException {
        // lc.ajouterLigne(prod, p);
        // this.ajouterCommande(p, u);
        int id_panier=0;
         String r = "select id_commande from `commande` where id_userr=?";
            PreparedStatement pst = cnx.prepareStatement(r);
            pst.setInt(1, id);
            ResultSet res = pst.executeQuery();
            
            while (res.next()) {

              id_panier=res.getInt("id_commande");

            }
        if (existePanier(id)) {
            String req = "select id_produit from `ligne_commande` where id_commande=?";
       PreparedStatement  ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet resultat = ps.executeQuery();
            
            while (resultat.next()) {

                if (resultat.getInt("id_produit") == prod.getId_produit()) {
                    System.out.println("Produit déjà ajouté");
                } else {
                    lc.ajouterLigne(prod.getId_produit(),id_panier);
                }

            }
        } else {
            String req = "INSERT INTO `panier`(`date_ajout`,`id_user`) VALUES (?,?)";
            PreparedStatement pstm = cnx.prepareStatement(req);
            pstm.setDate(1, (java.sql.Date) p.getDate_com());
           // pstm.setBoolean(2, p.isEtat());
            pstm.setInt(3, id);
            pstm.executeUpdate();
            System.out.println("Insertion panier done");
            lc.ajouterLigne(prod.getId_produit(),id_panier);
        }
     }
     public void ajouterAlaBaseCommande (Commande p , User u) {
         try {
             
             System.out.println("connexion établie");
             Statement stm = cnx.createStatement();
             String req = "INSERT INTO `commande`(`id_user`, `date`, `etat`, `total`) VALUES('"+u.getId()+"','"+p.getDate_com()+"','"+p.getEtat()+"','"+p.getTotal()+"')";
             stm.executeUpdate(req);
             
         } catch (SQLException ex) {
             Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
    
   
    

  public  ArrayList<Commande> getAllCommande() throws SQLException {
       ArrayList<Commande> retour = new ArrayList<>();
       Statement stm = cnx.createStatement();
        String req = "SELECT * FROM commande";
        ResultSet resultat = stm.executeQuery(req);
        while(resultat.next()){
           int id= resultat.getInt(1);
            int id_us = resultat.getInt(2);
           Date da= resultat.getDate(3);
            boolean et= resultat.getBoolean(4);
            float tot=resultat.getFloat(5);
            
           retour.add(new Commande(id_us, id_us, da, tot,et));
            
        }
            
        return retour;
    }
   
    
    public void supprimerCommande(Commande p) throws SQLException
    {
         Statement stm = cnx.createStatement();
        String req = "DELETE FROM `commande` WHERE `id_user`='"+p.getId_user()+"' ";
    stm.executeUpdate(req);
        //.out.println(p.getId_produit());
    
    }
    public void modifierCommande(Commande p, User u) throws SQLException
    {
        Statement stm = cnx.createStatement();
        String req = "UPDATE `commande` SET `id_user`='"+u.getId()+"',`date`='"+p.getDate_com()+"',`etat`='"+p.getEtat()+"',`total`='"+p.getTotal()+"' WHERE id_commande='"+p.getId_commande()+"' " ;
     stm.executeUpdate(req); 
    }
    public Date getDate(int id) throws SQLException
    {
        Date date = null ;
                PreparedStatement pt = cnx.prepareStatement("SELECT date FROM `commande` WHERE id_commande=?");
pt.setInt(1, id);
ResultSet resultat = pt.executeQuery();
 while (resultat.next()) {
        date  = resultat.getDate("date");
 }
 return date;
         
    }
    public ObservableList<Produit> loadPanier() throws SQLException {
     
        //list.setItems(items);
//        Statement stm = cnx.createStatement();
//        String req = "SELECT id FROM fos_user WHERE `enabled`=1 and `roles`=\"client\"";
//        ResultSet res = stm.executeQuery(req);
//        int id_user = res.getInt(1);
//String req="";
     ObservableList<Produit> items =FXCollections.observableArrayList();
        PreparedStatement pt = cnx.prepareStatement("SELECT produit.id_produit,produit.nom,produit.image,produit.prix,commande.date,ligne_commande.quantite FROM ligne_commande ,`produit` ,`commande` WHERE commande.id_commande IN (select id_commande from commande where id_user=?)and commande.id_commande=ligne_commande.id_commande and produit.id_produit=ligne_commande.id_produit and commande.etat=?");
        pt.setInt(1, 1);
        pt.setInt(2, 0);
        ResultSet resultat = pt.executeQuery();
        while (resultat.next()) {
           
            String nom = resultat.getString("nom");
           // items.add("Nom:   " + nom);
           // productsNames.add(nom);
            int prix = resultat.getInt("prix");
            String image= resultat.getString("image");
          //  items.add("Prix:   " + Float.toString(prix));
           // Date date = resultat.getDate("date_ajout");
            int quantite = resultat.getInt("quantite");
            Produit p=new Produit (resultat.getInt(1),nom,prix,image);
            items.add(p);
          //  items.add("Quantité:  " + Integer.toString(quantite));

           // items.add("---------------------------------------------------------------");
            

        }
        return items;
    }
    
    
    
    
    
    
    
    
    
}
