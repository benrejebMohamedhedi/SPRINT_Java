/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Lenovo
 */
public class Prestataire {
    
    private int id_prestataire;
    private String nom;
   private String prenom;

    public Prestataire() {
    }

    public Prestataire(int id_prestataire, String nom, String prenom) {
        this.id_prestataire = id_prestataire;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId_prestataire() {
        return id_prestataire;
    }

    public void setId_prestataire(int id_prestataire) {
        this.id_prestataire = id_prestataire;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id_prestataire;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestataire other = (Prestataire) obj;
        if (this.id_prestataire != other.id_prestataire) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prestataire{" + "id_prestataire=" + id_prestataire + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
   
}
