/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.logging.Logger;

/**
 *
 * @author karim
 */
public class prestataire {
private int id_service ; 
private String nom ; 
private String prenom ; 
private String  photo_profil ;

    public prestataire(int id_service, String nom, String prenom, String photo_profil) {
        this.id_service = id_service;
        this.nom = nom;
        this.prenom = prenom;
        this.photo_profil = photo_profil;
    }

    public prestataire(String nom, String prenom, String photo_profil) {
        this.nom = nom;
        this.prenom = prenom;
        this.photo_profil = photo_profil;
    }

    public prestataire() {
    }
    
    

    public int getId_service() {
        return id_service;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPhoto_profil() {
        return photo_profil;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPhoto_profil(String photo_profil) {
        this.photo_profil = photo_profil;
    }

    @Override
    public String toString() {
        return "prestataire{" + "id_service=" + id_service + ", nom=" + nom + ", prenom=" + prenom + ", photo_profil=" + photo_profil + '}';
    }
   



    
}
