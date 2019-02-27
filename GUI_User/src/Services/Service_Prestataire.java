/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.ConnexionDB;
import Entity.Client;
import Entity.Prestataire;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author NacimGastli
 */
public class Service_Prestataire implements CRUD<Prestataire> {

    private Statement ste;
    private PreparedStatement pst, pst2;
    private ResultSet rs;
    private ConnexionDB con;

    public Service_Prestataire() {
        con = ConnexionDB.getInstance();
    }

    public int SignIn(Prestataire p) throws SQLException {
    String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLES= ? and (EMAIL = ? OR USERNAME = ?) ";

        pst = ConnexionDB.getCnx().prepareStatement(requete);
        pst.setString(1,p.getPassword());
        pst.setInt(2, 2);
        pst.setString(3,p.getEmail());
        pst.setString(4,p.getUsername());
        rs = pst.executeQuery();

        boolean v = rs.next();

        if (v == true) {
            if (rs.getInt("enabled") == 0) {

                return 1;
            }
            if (rs.getInt("desactiver") == 1) {

                return 2;
            } else {
                return 3;
            }

        }
        return 0;

    }

    @Override
    public int insert(Prestataire p) {
          String md5 = getMd5(p.getPassword());
        try {
            String requete = "INSERT INTO fos_user(email,username,password,prenom,nom,cin,sexe,date_naissance,adresse,num_tel,roles,enabled,specialite) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            pst = ConnexionDB.getCnx().prepareStatement(requete);
            pst.setString(1, p.getEmail());
            pst.setString(2, p.getUsername());
            pst.setString(3, md5);
            pst.setString(4, p.getPrenom());
            pst.setString(5, p.getNom());
            pst.setString(6, p.getCin());
            pst.setString(7, p.getSexe());
            pst.setDate(8, java.sql.Date.valueOf(p.getDate_naissance()));
            pst.setString(9, p.getAdresse());
            pst.setString(10, p.getNum_tel());
            pst.setInt(11, 2);
            pst.setInt(12, 0);
            pst.setString(13, p.getSpecialite());

            pst.executeUpdate();

            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Prestataire.class.getName()).log(Level.SEVERE, null, ex);

            return 0;
        }

    }

    public int VerifierCompte(Prestataire p, String code) {
         
        try {
            String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLEs= ? and (EMAIL = ? OR USERNAME = ?) ";
            pst = ConnexionDB.getCnx().prepareStatement(requete);
            pst.setString(1, p.getPassword());
            pst.setInt(2, 2);
            pst.setString(3, p.getEmail());
            pst.setString(4, p.getUsername());

            rs = pst.executeQuery();

            boolean v = rs.next();

            if (v == true) {
                if (rs.getString("code").equals(code) == false) {
                    return 0;
                } else {
                    int id = rs.getInt("id");
                    System.out.println(id);
                    String req = "UPDATE fos_user set enabled=? where id=? ";
                    try {
                        pst2 = ConnexionDB.getCnx().prepareStatement(req);
                        pst2.setInt(1, 1);
                        pst2.setInt(2, id);
                        pst2.executeUpdate();
                        return 1;
                    } catch (SQLException ex) {
                        Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    public Prestataire chercher(int id) {
        try {
            Prestataire p = new Prestataire();
            String requete = "SELECT * FROM fos_user Where id=? ";
            pst = ConnexionDB.getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            while (rs.next()) {
                Prestataire R = new Prestataire(rs.getInt("id"), rs.getString("email"),
                        rs.getString("username"), rs.getString("password"), rs.getString("prenom"),
                        rs.getString("nom"), rs.getString("adresse"), rs.getString("num_tel"), rs.getString("photo_profil"));
                return R;
            }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int update(Prestataire p) {
        String requete = "UPDATE fos_user set email=?, username=?,  prenom=?, nom=?, adresse=?, num_tel=? where id = ?";
        try {
            pst = ConnexionDB.getCnx().prepareStatement(requete);
            pst.setString(1, p.getEmail());
            pst.setString(2, p.getUsername());
            pst.setString(3, p.getPrenom());
            pst.setString(4, p.getNom());
            pst.setString(5, p.getAdresse());
            pst.setString(6, p.getNum_tel());
            pst.setInt(7, p.getid());
            System.out.println(p);
            pst.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    //@Override
    public void Delete(int id) {
        String req = "UPDATE fos_user set desactiver=? where id=? ";
        try {
            pst2 = ConnexionDB.getCnx().prepareStatement(req);
            pst2.setInt(1, 1);
            pst2.setInt(2, id);
            pst2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Prestataire> getshow() {

        try {
            ObservableList<Prestataire> listService = FXCollections.observableArrayList();

            String req = "SELECT * FROM fos_user where roles='2'";
            pst = ConnexionDB.getCnx().prepareStatement(req);
            rs = pst.executeQuery();
            while (rs.next()) {
                Prestataire R = new Prestataire(rs.getString("specialite"), rs.getString("etat"), rs.getInt("id"), rs.getString("email"),
                        rs.getString("username"), rs.getString("prenom"),
                        rs.getString("nom"), rs.getString("cin"),
                        rs.getString("num_tel"), rs.getInt("enabled"), rs.getInt("desactiver"));

                listService.add(R);

            }
            return listService;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    @Override
    public int SingIn(Prestataire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

       @Override
    public String getMd5(String input) {
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  
  
        // For specifying wrong message digest algorithms 
        catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
    }



}
