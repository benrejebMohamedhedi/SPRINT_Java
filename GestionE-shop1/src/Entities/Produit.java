/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author nizar
 */

public class Produit {
    private int id_produit ;

    public Produit(int id_produit, String Nom, int prix, String image) {
        this.id_produit = id_produit;
        this.Nom = Nom;
        this.prix = prix;
        this.image = image;
    }

    public Produit(String Nom, int quantite, int prix,String img) {
        this.Nom = Nom;
        this.quantite = quantite;
        this.prix = prix;
        this.image=img;
    }

    public Produit(int id_produit, int quantite, int prix, String img) {
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.prix = prix;
        this.image=img;
    }
    private String Nom;
    private String reference;
    private String cateorie;
    private int quantite;
    private int prix;
    private String image ;
    private String description ;
    private int etoile;

    public void setEtoile(int etoile) {
        this.etoile = etoile;
    }

    public int getEtoile() {
        return etoile;
    }

    public Produit() {
    }

    
    public Produit(int id_produit, String Nom, String reference, String cateorie, int quantite, int prix, String image, String description) {
        this.id_produit = id_produit;
        this.Nom = Nom;
        this.reference = reference;
        this.cateorie = cateorie;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
        this.description = description;
    }

    public Produit(String Nom, String reference, String cateorie, int quantite, int prix, String image, String description) {
        this.Nom = Nom;
        this.reference = reference;
        this.cateorie = cateorie;
        this.quantite = quantite;
        this.prix = prix;
        this.image = image;
        this.description = description;
    }

    public int getId_produit() {
        return id_produit;
    }

    public String getNom() {
        return Nom;
    }

    public String getReference() {
        return reference;
    }

    public String getCateorie() {
        return cateorie;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getPrix() {
        return prix;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public void setCateorie(String cateorie) {
        this.cateorie = cateorie;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id_produit;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit other = (Produit) obj;
        if (this.id_produit != other.id_produit) {
            return false;
        }
        if (!Objects.equals(this.reference, other.reference)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produit{" + "id_produit=" + id_produit + ", Nom=" + Nom + ", reference=" + reference + ", cateorie=" + cateorie + ", quantite=" + quantite + ", prix=" + prix + ", image=" + image + ", description=" + description + '}';
    }
    
    
    
}
