/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.sun.javafx.fxml.expression.BinaryExpression;
import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class Demande {
    private int id_demande;
    private int id_service;
    private int id_user;
    private String description;
    private int prix;
    private String image;
    private Date date_rdv;
    private  int statut;
    private int id_prestataire;
    private Prestataire prestataire;
    private User user;
public enum statut {
    Valide,
    NonValide;
    
}
    
    public Demande(){}
    public Demande(int id_demande, int id_service, int id_user,int id_prestataire, String description, int prix, String image, Date date_rdv) {
        this.id_demande = id_demande;
        this.id_service = id_service;
        this.id_user = id_user;
        this.id_prestataire=id_prestataire;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.date_rdv = date_rdv;
       // this.Statut=statut;
    }
    
   /*  public Demande(int id_demande, int id_service, int id_user, String description, int prix,String image, Date date_rdv) {
        this.id_demande = id_demande;
        this.id_service = id_service;
        this.id_user = id_user;
        this.description = description;
        this.prix = prix;
        this.image=image;
        this.date_rdv = date_rdv;
         }*/
public Demande(int id_service, int id_user, String description, int prix, String image, Date date_rdv,int statut) {
        //this.id_demande = id_demande;
        this.id_service = id_service;
        this.id_user = id_user;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.date_rdv = date_rdv;
        this.statut=statut;
       
        
}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Prestataire getPrestataire() {
        return prestataire;
    }

    public void setPrestataire(Prestataire prestataire) {
        this.prestataire = prestataire;
    }

public Demande(int id_demande,int prestataire, String description, int prix, String image, Date date_rdv) {
        this.id_demande = id_demande;
        //this.id_service = id_service;
        this.id_prestataire = id_prestataire;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.date_rdv = date_rdv;
       // this.statut=statut;
       
        
}


    public Demande(String description, int prix,String image , Date date_rdv) {
        this.description = description;
        this.prix = prix;
        this.image=image;
        this.date_rdv = date_rdv;
    }

    public int getId_prestataire() {
        return id_prestataire;
    }

    public void setId_prestataire(int id_prestataire) {
        this.id_prestataire = id_prestataire;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }


    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

  
    

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(Date date_rdv) {
        this.date_rdv = date_rdv;
    }
  

    @Override
    public int hashCode() {
       
        return id_demande;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Demande other = (Demande) obj;
        if (this.id_demande != other.id_demande) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Demande{" + "id_demande=" + id_demande + ", id_service=" + id_service + ", id_user=" + id_user + ", description=" + description + ", prix=" + prix + ", image=" + image + ", date_rdv=" + date_rdv + '}';
    }
    
    
    
    
}
