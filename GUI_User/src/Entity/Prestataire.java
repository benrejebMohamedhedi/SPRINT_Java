/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Database.ConnexionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.scene.control.ComboBox;

/**
 *
 * @author NacimGastli
 */
public class Prestataire extends User {
  private String Specialite;
  private String Etat;
  private int ID_Service;

    public Prestataire(String username, String password) {
        super(username, password);
    }

//    public Prestataire(String Specialite, String Etat, int ID_Service, int id, String email, String username, String password, String prenom, String nom, String cin, String sexe, LocalDate date_naissance, String adresse, String num_tel, String photo_profil, int status, String role) {
//        super(id, email, username, password, prenom, nom, cin, sexe, date_naissance, adresse, num_tel, photo_profil, status, "Prestataire");
//        this.Specialite = Specialite;
//        this.Etat = Etat;
//        this.ID_Service = ID_Service;
//    }

    public Prestataire(String Specialite, String email, String username, String password, String prenom, String nom, String cin, String sexe, LocalDate date_naissance, String adresse, String num_tel, int status, int role) {     
        super(email, username, password, prenom, nom, cin, sexe, date_naissance, adresse, num_tel, status, role);
        this.Specialite = Specialite;
        
        
    }

    public Prestataire(int id, String email, String username, String prenom, String nom, String adresse, String num_tel, String photo_profil) {
        super(id, email, username, prenom, nom, adresse, num_tel, photo_profil);
    }

    public Prestataire(int id, String email, String username, String password, String prenom, String nom, String adresse, String num_tel, String photo_profil) {
        super(id, email, username, password, prenom, nom, adresse, num_tel, photo_profil);
    }
    
    public Prestataire() {
    }
     public Prestataire(int id,String Prenom, String Nom) {
        super(id, Prenom, Nom);
    }
    public Prestataire Session(Prestataire p) throws SQLException {
        Statement ste;
        PreparedStatement pst;
        ResultSet rs;
        ConnexionDB con ;
        con = ConnexionDB.getInstance();
        String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLES= ? and (EMAIL = ? OR USERNAME = ?) ";

        pst = ConnexionDB.getCnx().prepareStatement(requete);
        pst.setString(1, p.getPassword());
        pst.setInt(2, 2);
        pst.setString(3, p.getEmail());
        pst.setString(4, p.getUsername());
        rs = pst.executeQuery();
        
        while (rs.next()) {
           Prestataire R = new Prestataire( rs.getInt("id"),rs.getString("prenom"), rs.getString("nom"));

            R.setNom(rs.getString("nom"));
            R.setPrenom(rs.getString("prenom"));
            R.setid(rs.getInt("id"));

            return R;
        }
        return null;

    }

    public String getSpecialite() {
        return Specialite;
    }

    public void setSpecialite(String Specialite) {
        this.Specialite = Specialite;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public int getID_Service() {
        return ID_Service;
    }

    public void setID_Service(int ID_Service) {
        this.ID_Service = ID_Service;
    }

    @Override
    public String toString() {
        return "Prestataire{"+ "id=" + getid() + ", email=" + getEmail() + ", username=" + getUsername() + ", password=" + getPassword() + ", prenom=" + getPrenom() + ", nom=" + getNom() + ", cin=" + getCin() + ", sexe=" + getSexe() + ", date_naissance=" + getDate_naissance() + ", Specialite=" + getSpecialite() + ", Etat=" + getEtat() + '}';
    }
  
}
