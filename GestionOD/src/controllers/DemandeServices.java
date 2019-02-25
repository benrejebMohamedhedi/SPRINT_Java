/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Demande;
import Entities.Prestataire;
import Entities.Prestation;
import connexion.conDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Lenovo
 */
public class DemandeServices {
  static Connection cnx;
    
    public DemandeServices() throws SQLException {
       cnx = conDB.getInstance().getCnx();
    }    
    
 

	public  ObservableList<Demande> findAll() throws SQLException {
		ObservableList<Demande> AllDemande = FXCollections.observableArrayList() ;
		Demande demande;
                Statement stm = cnx.createStatement();
		String query="SELECT * FROM demande D JOIN users U ON U.id_user=D.id_user ";
	try {
		stm = cnx.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while(rs.next()) {
			demande = new Demande(rs.getInt("id_demande"),rs.getInt("id_service"),rs.getInt("id_user"),rs.getInt("id_prestataire"),rs.getString("Description"),rs.getInt("Prix"),rs.getString("image"),rs.getDate("date_rdv"));
			AllDemande.add(demande);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return AllDemande;
	}   
        
        
          public static int InsertPrestation(Prestation P) throws SQLException
           {
               int ps = 0;
           try {
             java.util.Date date_util=new java.util.Date();
             java.sql.Date date_sql=new java.sql.Date(date_util.getTime());
              
               String req ="INSERT INTO `prestations`( id_demande,id_prestataire,id_user,date_rdv) VALUES(?,?,?,?) ";
                PreparedStatement pstm = cnx.prepareStatement(req);
               
                  pstm.setInt(1,P.getId_demande());
                  pstm.setInt(2,P.getId_prestataire());
                  pstm.setInt(3,P.getId_user());
                  pstm.setDate(4, P.getDate_rdv());
                 
           
             ps= pstm.executeUpdate();
               System.out.println("trying");
              
                  } catch (SQLException ex) {
             Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
         }
           return ps;
           }
   public void ajouterDemande(Demande d) {
       
          try {
             java.util.Date date_util=new java.util.Date();
             java.sql.Date date_sql=new java.sql.Date(date_util.getTime());
             String req = "INSERT INTO `demande`( `id_service`,`id_user`,`id_prestataire`,`Description`,`Prix`, `image`,`date_rdv`,`statut`) VALUES (?,?,?,?,?,?,?,?)";
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1, d.getId_service());
             pstm.setInt(2,d.getId_user());
             pstm.setInt(3,d.getId_prestataire());
             pstm.setString(4, d.getDescription());
            
              pstm.setInt(5, d.getPrix());
              pstm.setString(6,d.getImage());
             
                pstm.setDate(7,d.getDate_rdv());
                pstm.setInt(8,d.getStatut());
             pstm.executeUpdate();
               System.out.println("aa");
         } catch (SQLException ex) {
             Logger.getLogger(DemandeServices.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
  
       
            public ObservableList<Demande> fillprestatireLIst(int id) throws SQLException {
		ObservableList<Demande> AllDemande = FXCollections.observableArrayList() ;
		Demande demande;
                Statement stm = cnx.createStatement();
		String query="  Select * from demande where id_prestataire='"+id+"'";
	try {
		stm = cnx.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while(rs.next()) {
			demande = new Demande(rs.getInt("id_demande"),rs.getInt("id_service"),rs.getInt("id_user"),rs.getInt("id_prestataire"),rs.getString("Description"),rs.getInt("Prix"),rs.getString("image"),rs.getDate("date_rdv"));
			AllDemande.add(demande);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return AllDemande;
	}   
            
            
            public ObservableList<Demande> AfficherListDemandePerson() throws SQLException {
		ObservableList<Demande> AllDemande = FXCollections.observableArrayList() ;
		Demande demande;
                Statement stm = cnx.createStatement();
		String query="  Select *from demande d join prestataire p on   d.id_prestataire =p.id_prestataire ";
	try {
		stm = cnx.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while(rs.next()) {
			demande = new Demande(rs.getInt("id_demande"),rs.getInt("id_service"),rs.getInt("id_user"),rs.getInt("id_prestataire"),rs.getString("Description"),rs.getInt("Prix"),rs.getString("image"),rs.getDate("date_rdv"));
			AllDemande.add(demande);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return AllDemande;
	}   
              public ObservableList<Prestataire> fillprestatireLIstt() throws SQLException {
		ObservableList<Prestataire> Prestaires = FXCollections.observableArrayList() ;
		Prestataire prestataire;
                Statement stm = cnx.createStatement();
		String query="  Select * from prestataire P " ;
	try {
		stm = cnx.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while(rs.next()) {
			Prestataire P= new Prestataire(rs.getInt("id_prestataire"),rs.getString("nom"),rs.getString("prenom"));
			Prestaires.add(P);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return Prestaires;
	}   
                 public ObservableList<Demande> fillOffre() throws SQLException {
		ObservableList<Demande> demandes = FXCollections.observableArrayList() ;
		Prestataire prestataire;
                Statement stm = cnx.createStatement();
		String query="  Select * from demande  " ;
	try {
		stm = cnx.createStatement();
		ResultSet rs = stm.executeQuery(query);
		while(rs.next()) {
			Demande d= new Demande(rs.getInt("id_demande"),rs.getInt("id_service"),rs.getInt("id_user"),rs.getInt("id_prestataire"),rs.getString("Description"),rs.getInt("Prix"),rs.getString("image"),rs.getDate("date_rdv"));
			demandes.add(d);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
		return demandes;
	}   
          
         
            
       public static int deleteDemande(Demande d) throws SQLException{
    int st=0;
      
             String req = "Delete   from demande  WHERE id_demande =? ";
             
             
             PreparedStatement pstm = cnx.prepareStatement(req);
             pstm.setInt(1 ,d.getId_demande());
          st =  pstm.executeUpdate();
          return st;

       }
       
        public static int    update(Demande d ) throws SQLException  {
        int st=0;
        
            
        
   String req = "UPDATE `demande` SET `Description`=?,`Prix`=? ,`date_rdv`=?   WHERE `id_demande`= ?"; 
   //conDB.class.getName();
  // PreparedStatement pstm     ;
  PreparedStatement pstm     =   cnx.prepareStatement(req);
  // Connection cnx= pstm.PrepareStatement(req);
     pstm.setString(1, d.getDescription());
    pstm.setInt(2, d.getPrix());
    pstm.setInt(4 ,d.getId_demande());
    pstm.setDate(3, d.getDate_rdv());
     st=pstm.executeUpdate();
    
        
       
       
    
 return st ; 
}
       public Demande fetchDemande(ResultSet rs) throws SQLException {
		
			Demande Ds = new Demande();
			Ds.setId_demande(rs.getInt("id_demande"));
                         Ds.setId_service(rs.getInt("id_service"));
                            Ds.setPrix(rs.getInt("Prix"));
                         Ds.setDescription(rs.getString("Description"));
                         Ds.setImage(rs.getString("image"));
                      
			Ds.setDate_rdv(rs.getDate("date_rdv"));
                       
                        
			
			return Ds;
	
	
	}
       
	
       public List<Demande> findByDate(java.util.Date date) throws SQLException {
		List<Demande> demandes = new ArrayList<>();
		String query="SELECT * FROM demande where date_rdv = ?";
		
		
			PreparedStatement psmt = cnx.prepareStatement(query);
			 psmt.setDate(1, new java.sql.Date(date.getTime()));
			ResultSet rs = psmt.executeQuery();
			
			
			while(rs.next()) {
				Demande Ds = fetchDemande(rs);
				demandes.add(Ds);
			}
		
		return demandes;
	}
       
    //   insert into entry values(gen_id(IDENT, 1), 'TODAY', '11111111', 'ESPECE', '', 'écriture 1', 55, 'DEBIT', 'NON', select id from account where label='sct A');

       
       //FinByStatutDemande
      /* public List<Demande> findByStatut(String statut) throws SQLException {
		// TODO Auto-generated method stub
		List<Demande> demandes = new ArrayList<Demande>();
		String query="SELECT * FROM demande where statut like ?" ;
		
		
			PreparedStatement psmt = cnx.prepareStatement(query);
			psmt.setString(1, statut);
			ResultSet rs = psmt.executeQuery();
			
			while(rs.next()) {
				Demande dms = fetchDemande(rs);
				demandes.add(dms);
			}
		
		
		return demandes;
	}
	*/
       
       
       
       
	}

    

