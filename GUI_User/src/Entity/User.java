/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author NacimGastli
 */
public class User {
  private int id;
  private String email;
  private String username; 				
  private String password; 				
  private String prenom; 				
  private String nom; 					
  private String cin; 					
  private String sexe; 					
  private LocalDate date_naissance; 		
  private String adresse; 				
  private String num_tel; 				
  private String photo_profil; 			
  private int status ;		
  private int role;
  public User(){
  this.nom="";
  this.prenom="";}
public User(String nom, String password) {
        this.username = nom;
        this.password = password;
    }

    public User(int id, String email, String username, String password, String prenom, String nom, String cin, String sexe, LocalDate date_naissance, String adresse, String num_tel, String photo_profil, int status, int role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
        this.status = status;
        this.role = role;
    }

    public User(String email, String username, String password, String prenom, String nom, String cin, String sexe, LocalDate date_naissance, String adresse, String num_tel, int status, int role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.sexe = sexe;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.status = status;
        this.role = role;
    }
     public User(String email, String username, String password, String prenom, String nom, String cin, String sexe, String adresse, String num_tel, int status, int role) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.cin = cin;
        this.sexe = sexe;
    
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.status = status;
        this.role = role;
    }
    
   


 
    public User(int id,String prenom, String nom) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
    }

    public User(int id, String email, String username, String password, String prenom, String nom, String adresse, String num_tel, String photo_profil) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
    }

    public User(int id,String email, String username, String prenom, String nom, String adresse, String num_tel, String photo_profil) {
        this.id=id;
        this.email = email;
        this.username = username;
        this.prenom = prenom;
        this.nom = nom;
        this.adresse = adresse;
        this.num_tel = num_tel;
        this.photo_profil = photo_profil;
    }
    

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getPhoto_profil() {
        return photo_profil;
    }

    public void setPhoto_profil(String photo_profil) {
        this.photo_profil = photo_profil;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", username=" + username + ", password=" + password + ", prenom=" + prenom + ", nom=" + nom + ", cin=" + cin + ", sexe=" + sexe + ", date_naissance=" + date_naissance + '}';
    }
    

   
}
