/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Lenovo
 */
public class Prestation {
    
    private int id_prestation;
   private int id_demande;
   private int id_prestataire;
   private int id_user;
   private Date date_rdv;

    public Prestation() {
    }

    public Prestation(int id_prestation, int id_demande, int id_prestataire, Date date_rdv) {
        this.id_prestation = id_prestation;
        this.id_demande = id_demande;
        this.id_prestataire = id_prestataire;
        this.date_rdv = date_rdv;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_prestation() {
        return id_prestation;
    }

    public void setId_prestation(int id_prestation) {
        this.id_prestation = id_prestation;
    }

    public int getId_demande() {
        return id_demande;
    }

    public void setId_demande(int id_demande) {
        this.id_demande = id_demande;
    }

    public int getId_prestataire() {
        return id_prestataire;
    }

    public void setId_prestataire(int id_prestataire) {
        this.id_prestataire = id_prestataire;
    }

    public Date getDate_rdv() {
        return date_rdv;
    }

    public void setDate_rdv(Date date_rdv) {
        this.date_rdv = date_rdv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id_prestation;
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
        final Prestation other = (Prestation) obj;
        if (this.id_prestation != other.id_prestation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Prestation{" + "id_prestation=" + id_prestation + ", id_demande=" + id_demande + ", id_prstataire=" + id_prestataire + ", date_rdv=" + date_rdv + '}';
    }
    
   
}
