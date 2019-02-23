/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services.Produit;

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

/**
 *
 * @author nizar
 */
public class ProduitService {
    public Connection cnx; 

    public ProduitService() throws SQLException {
        cnx = MyDBcon.getInstance().getCnx();
    }
    
   

   public void ajouterAlaBaseProduit (Produit p) {
         try {
             
             System.out.println("connexion établie");
             Statement stm = cnx.createStatement();
             String req = "INSERT INTO `produit`(`nom`, `reference`, `categorie`, `quantite`, `prix`, `image`, `description`) VALUES('"+p.getNom()+"','"+p.getReference()+"','"+p.getCateorie()+"','"+p.getQuantite()+"','"+p.getPrix()+"','"+p.getImage()+"','"+p.getDescription()+"')";
             stm.executeUpdate(req);
             
         } catch (SQLException ex) {
             Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }
    public Produit getBysh(String reference) throws SQLException
   {
       
       
        String req = "SELECT `id_produit`, `nom`, `reference`, `categorie`, `quantite`, `prix`, `image`, `description` FROM `produit` WHERE `reference`='"+reference+"' or `nom`='"+reference+"' or `categorie`='"+reference+"'  ";
     Produit p = new Produit();
     Statement stm= cnx.createStatement();
      
                 ResultSet resultat = stm.executeQuery(req);
                 if(resultat.next())
                 {
                     //nom ref cat prix qua ima
                             p.setNom(resultat.getString(2));
                             p.setReference(resultat.getString(3));
                             p.setCateorie(resultat.getString(4));
                             p.setQuantite(resultat.getInt(5));
                             p.setPrix(resultat.getInt(6));
                             p.setImage(resultat.getString(7));
                             p.setDescription(resultat.getString(8));
                             
                 }
                 return p;
                             
                             
                    
                 }

      
     
   
   public Produit getById(int ID) throws SQLException
   {
       
        Statement stm = cnx.createStatement();
        String req = "SELECT * FROM produit  WHERE `id_produit`= '"+ID+"' ";
        ResultSet resultat = stm.executeQuery(req);
       int id= resultat.getInt(1);
            String nom = resultat.getString("nom");
           String ref= resultat.getString("reference");
            String Cat= resultat.getString("categorie");
            int qt=resultat.getInt("quantite");
            int prx =resultat.getInt("prix");
            String img=resultat.getString("image");
            String desc= resultat.getString("description");
        Produit p = new Produit(id,nom,ref,Cat,qt, prx,img,desc);
         return p;
   }
    
   
    
public ArrayList<Integer> getAllIds() throws SQLException
{
    ArrayList<Integer> li =new ArrayList<>();
    Statement stm = cnx.createStatement();
        String req = "SELECT `id_produit` FROM produit";
        ResultSet resultat = stm.executeQuery(req);
         while(resultat.next()){
           int id= resultat.getInt(1);
           li.add(id);
         }
         return li;
         
}
public ArrayList<String> getImages () throws SQLException
{
     ArrayList<String> retour = new ArrayList<>();
       Statement stm = cnx.createStatement();
        String req = "SELECT image FROM produit";
        ResultSet resultat = stm.executeQuery(req);
        while(resultat.next()){
          
            String img=resultat.getString("image");
            
           retour.add(img);
            
        }
            
        return retour;
    
}
  public  ArrayList<Produit> getAllProduit() throws SQLException {
       ArrayList<Produit> retour = new ArrayList<>();
       Statement stm = cnx.createStatement();
        String req = "SELECT * FROM produit";
        ResultSet resultat = stm.executeQuery(req);
        while(resultat.next()){
           int id= resultat.getInt(1);
            String nom = resultat.getString("nom");
           String ref= resultat.getString("reference");
            String Cat= resultat.getString("categorie");
            int qt=resultat.getInt("quantite");
            int prx =resultat.getInt("prix");
            String img=resultat.getString("image");
            String desc= resultat.getString("description");
           retour.add(new Produit(id, nom,ref,Cat,qt, prx,img,desc));
            
        }
            
        return retour;
    }
    public void supprimerPrd(String ref) throws SQLException
    {
         Statement stm = cnx.createStatement();
        String req = "DELETE FROM `produit` WHERE `reference` like '%"+ref+"%' ";
    stm.executeUpdate(req);
        //.out.println(p.getId_produit());
    
    }
    public void modifierProduit(Produit p) throws SQLException
    {
        Statement stm = cnx.createStatement();
        String req = "UPDATE `produit` SET `nom`='"+p.getNom()+"',`reference`='"+p.getReference()+"',`categorie`='"+p.getCateorie()+"',`quantite`='"+p.getQuantite()+"',`prix`='"+p.getPrix()+"',`image`='"+p.getImage()+"',`description`='"+p.getDescription()+"' WHERE `reference`= '"+p.getReference()+"' ";
        stm.executeUpdate(req); 
        
    }
    
    
    
}
