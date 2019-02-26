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


/**
 *
 * @author NacimGastli
 */
public class Client extends User {
    private float Nb_Points;

//    public Client(float Nb_Points, int id, String email, String username, String password, String prenom, String nom, String cin, String sexe, LocalDate date_naissance, String adresse, String num_tel, String photo_profil, int status, String role) {
//        super(id, email, username, password, prenom, nom, cin, sexe, date_naissance, adresse, num_tel, photo_profil, status, "Client");
//        this.Nb_Points = Nb_Points;
//    }

    public Client() {
    }

    public Client(float Nb_Points, String email, String username, String password, String prenom, String nom, String cin, String sexe, LocalDate date_naissance, String adresse, String num_tel, int status, int role) {
        super(email, username, password, prenom, nom, cin, sexe, date_naissance, adresse, num_tel, status, role);
        this.Nb_Points = Nb_Points;
    }

    public Client(float Nb_Points, int id, String email, String username, String password, String prenom, String nom, String adresse, String num_tel, String photo_profil) {
        super(id, email, username, password, prenom, nom, adresse, num_tel, photo_profil);
        this.Nb_Points = Nb_Points;
    }
    public Client(int id,String email, String username,String prenom, String nom, String adresse, String num_tel, String photo_profil) {
        super(id,email, username, prenom, nom, adresse, num_tel, photo_profil);
      
    }
    

    public Client(String username, String password) {
        super(username, password);
    }
    public Client(int id,String Prenom, String Nom) {
        super(id, Prenom, Nom);
    }
    public Client Session(Client p) throws SQLException {
        Statement ste;
        PreparedStatement pst;
        ResultSet rs;
        ConnexionDB con ;
        con = ConnexionDB.getInstance();
        String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLES= ? and (EMAIL = ? OR USERNAME = ?) ";

        pst = ConnexionDB.getCnx().prepareStatement(requete);
        pst.setString(1, p.getPassword());
        pst.setInt(2, 1);
        pst.setString(3, p.getEmail());
        pst.setString(4, p.getUsername());
        rs = pst.executeQuery();
        
        while (rs.next()) {
            
            Client R = new Client( rs.getInt("id"),rs.getString("prenom"), rs.getString("nom"));
            R.setNom(rs.getString("nom"));
            R.setPrenom(rs.getString("prenom"));
            R.setid(rs.getInt("id"));

            return R;
        }
        return null;

    }
   

    public float getNb_Points() {
        return Nb_Points;
    }

    public void setNb_Points(float Nb_Points) {
        this.Nb_Points = Nb_Points;
    }

    @Override
    public String toString() {
        return "Client{" + "id=" + getid() + ", email=" + getEmail() + ", username=" + getUsername() + ", password=" + getPassword() + ", prenom=" + getPrenom() + ", nom=" + getNom() + ", cin=" + getCin() + ", sexe=" + getSexe() + ", date_naissance=" + getDate_naissance() + "Nb_Points=" + Nb_Points + '}';
    }

   
    
   
}
