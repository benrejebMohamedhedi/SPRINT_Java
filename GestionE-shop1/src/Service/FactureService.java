/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DB.MyDBcon;
import Entities.Commande;
import Entities.Facture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

/**
 *
 * @author nizar
 */
public class FactureService {
     public Connection cnx; 

    public FactureService() throws SQLException {
         cnx = MyDBcon.getInstance().getCnx();
    }

   public void ajouterfacture(int id) throws SQLException
    { int count=0;
        PreparedStatement ptst = cnx.prepareStatement("select count(*) from facture where id_commande=?");
        ptst.setInt(1, id);
        ResultSet ss=ptst.executeQuery();
        while(ss.next())
        {
           count+=ss.getInt(1);
        }
        if (count==0)
        {
        Commande c= new Commande();
        PreparedStatement ptt = cnx.prepareStatement("select * from commande where id_commande=?");
        ptt.setInt(1, id);
        ResultSet res=ptt.executeQuery();
        while (res.next())
        {
            c.setId_commande(res.getInt("id_commande"));
            c.setTotal(res.getInt("total"));
            c.setId_commande(id);
            c.setId_user(res.getInt("id_user"));
        }
        PreparedStatement pt = cnx.prepareStatement("INSERT INTO `facture`(`id_user`, `id_commande`, `prix_totale`) VALUES (?,?,?) ");
        pt.setInt(1, c.getId_user());
        pt.setInt(2,id);
        pt.setInt(3,c.getTotal());
        pt.executeUpdate(); 
    }
        else
            System.out.println("facture existe");
    }
   
    public void ajouterfacture(Commande c,int id) throws SQLException
    {
        PreparedStatement pt = cnx.prepareStatement("INSERT INTO `facture`(`id_user`, `id_commande`, `prix_totale`) VALUES (?,?,?) ");
        pt.setInt(1, id);
        pt.setInt(2,c.getId_commande());
        pt.setInt(3,c.getTotal());
        pt.executeUpdate(); 
    }
     public Facture getFacturebyid(int id ) throws SQLException
     {
             Facture f = new Facture();
         PreparedStatement pt = cnx.prepareStatement("SELECT * FROM `facture` WHERE id_commande=? ");
        pt.setInt(1, id);
        ResultSet resultat = pt.executeQuery();
 while (resultat.next()) {
     f.setId_commande(resultat.getInt("id_commande"));
     f.setId_facture(resultat.getInt("id_facture"));
     f.setId_user(resultat.getInt("id_user"));
     f.setPrix_totale(resultat.getInt("prix_totale"));
 }
 return f;
     }
    
}
