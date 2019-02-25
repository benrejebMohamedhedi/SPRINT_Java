/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author nizar
 */
public class Facture {
   private int prix_totale ;
   private int id_facture ;
   private int id_commande;
   private int id_user ;

    public int getId_facture() {
        return id_facture;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Facture(int prix_totale, int id_facture, int id_commande, int id_user) {
        this.prix_totale = prix_totale;
        this.id_facture = id_facture;
        this.id_commande = id_commande;
        this.id_user = id_user;
    }

    public Facture() {
    }

    public Facture(int prix_totale) {
        this.prix_totale = prix_totale;
    }

    public int getPrix_totale() {
        return prix_totale;
    }

    public void setPrix_totale(int prix_totale) {
        this.prix_totale = prix_totale;
    }

    @Override
    public String toString() {
        return "Facture{" + "prix_totale=" + prix_totale + "\n, id_facture=" + id_facture + "\n, id_commande=" + id_commande + "\n, id_user=" + id_user + '}';
    }

   
   
    
}
