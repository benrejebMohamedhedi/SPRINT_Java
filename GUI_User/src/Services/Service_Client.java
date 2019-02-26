/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Database.ConnexionDB;
import Entity.Client;
import Entity.Prestataire;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NacimGastli
 */
public class Service_Client implements CRUD<Client> {

    private Statement ste;
    private PreparedStatement pst, pst2;
    private ResultSet rs, rs2;
    private ConnexionDB con;

    public Service_Client() {
        con = ConnexionDB.getInstance();
    }

    public int SignIn(Client p) throws SQLException {
//    String databaseUsername = "";
//    String databasePassword = "";

        String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLEs= ? and (EMAIL = ? OR USERNAME = ?) ";

        pst = ConnexionDB.getCnx().prepareStatement(requete);
        pst.setString(1, p.getPassword());
        pst.setInt(2, 1);
        pst.setString(3, p.getEmail());
        pst.setString(4, p.getUsername());

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
    public int insert(Client p) {
        String requete = "INSERT INTO fos_user(email, username, password, prenom, nom, cin,sexe,date_naissance,adresse,num_tel,roles,enabled,Nb_Points) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = ConnexionDB.getCnx().prepareStatement(requete);
            pst.setString(1, p.getEmail());
            pst.setString(2, p.getUsername());
            pst.setString(3, p.getPassword());
            pst.setString(4, p.getPrenom());
            pst.setString(5, p.getNom());
            pst.setString(6, p.getCin());
            pst.setString(7, p.getSexe());
            pst.setDate(8, java.sql.Date.valueOf(p.getDate_naissance()));
            pst.setString(9, p.getAdresse());
            pst.setString(10, p.getNum_tel());
            pst.setInt(11, 1);
            pst.setInt(12, 0);
            pst.setFloat(13, 0);

            pst.executeUpdate();
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public int VerifierCompte(Client p, String code) {
        try {
            String requete = "SELECT * FROM fos_user Where PASSWORD =? and ROLEs= ? and (EMAIL = ? OR USERNAME = ?) ";
            pst = ConnexionDB.getCnx().prepareStatement(requete);
            pst.setString(1, p.getPassword());
            pst.setInt(2, 1);
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
                        pst2.setInt(1,1);
                        pst2.setInt(2,id);
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
    public Client chercher(int id){
        try {
            Client p = new Client();
            String requete = "SELECT * FROM fos_user Where id=? ";
            pst = ConnexionDB.getCnx().prepareStatement(requete);
            pst.setInt(1,id);                     
            rs = pst.executeQuery();
            while (rs.next()) {
            Client R = new Client(rs.getFloat("Nb_Points"),rs.getInt("id"),rs.getString("email"),
            rs.getString("username"),rs.getString("password"),rs.getString("prenom"),
            rs.getString("nom"),rs.getString("adresse"),rs.getString("num_tel"),rs.getString("photo_profil"));  
            return R;
        }
            return p;
        } catch (SQLException ex) {
            Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void Delete(Client p, int id) {
//        String requete ="delete from plante where id = ? or name = ? ";
//        
//              try {
//                  pst = ConnexionDB.getCnx().prepareStatement(requete);
//                  pst.setString(2, p.getNom());
//                   pst.setInt(1,id);
//                   pst.executeUpdate();
//              } catch (SQLException ex) {
//                  Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
//              }

    }

    
    @Override
    public int update(Client p) {
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

//    @Override
//    public List<Client> getshow() {
//                List<Client> list = new ArrayList<>();
//            try {
//            String requete = "select * from User where role='Client' ";
//            rs= ste.executeQuery(requete);
//            while(rs.next()){
//                list.add(new Client(rs.getInt(1), rs.getString(2),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getDate(6),rs.getDate(7),rs.getInt(8)));
//            }
//            }
//            catch (SQLException ex) {
//                Logger.getLogger(Service_Client.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            return list;
//
//    }
    @Override
    public List<Client> getshow() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int SingIn(Client t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
