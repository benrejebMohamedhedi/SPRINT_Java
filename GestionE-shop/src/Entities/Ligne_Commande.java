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
public class Ligne_Commande {
    int ID_commande;
    int Id_Produit;
    int quantite;

    public Ligne_Commande() {
    }

    public Ligne_Commande(int quantite) {
        this.quantite = quantite;
    }

    public Ligne_Commande(int ID_commande, int Id_Produit, int quantite) {
        this.ID_commande = ID_commande;
        this.Id_Produit = Id_Produit;
        this.quantite = quantite;
    }

    public int getID_commande() {
        return ID_commande;
    }

    public int getId_Produit() {
        return Id_Produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setID_commande(int ID_commande) {
        this.ID_commande = ID_commande;
    }

    public void setId_Produit(int Id_Produit) {
        this.Id_Produit = Id_Produit;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Ligne_Commande{" + "ID_commande=" + ID_commande + ", Id_Produit=" + Id_Produit + ", quantite=" + quantite + '}';
    }
    
    
}
