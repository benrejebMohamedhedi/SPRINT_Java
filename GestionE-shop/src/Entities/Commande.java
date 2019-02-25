/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author nizar
 */
public class Commande {
    int id_commande;
    int Id_user ;
    Date date_com ;
    int total ;
    boolean etat ;

    public Commande(int id_us, int id_us0, Date da, float tot, boolean et) {
        this.id_commande = id_commande;
        this.Id_user = Id_user;
        this.date_com = date_com;
        this.total = total;
        this.etat=et;
    }

    public Commande() {
       
    }

    public boolean getEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public Commande(int id_commande, int Id_user, Date date_com, int total) {
        this.id_commande = id_commande;
        this.Id_user = Id_user;
        this.date_com = date_com;
        this.total = total;
    }

    public Commande(int Id_user, Date date_com, int total) {
        this.Id_user = Id_user;
        this.date_com = date_com;
        this.total = total;
    }

    public int getId_commande() {
        return id_commande;
    }

    public int getId_user() {
        return Id_user;
    }

    public Date getDate_com() {
        return date_com;
    }

    public int getTotal() {
        return total;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }

    public void setDate_com(Date date_com) {
        this.date_com = date_com;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Commande{" + "id_commande=" + id_commande + ", Id_user=" + Id_user + ", date_com=" + date_com + ", total=" + total + '}';
    }
    
}
