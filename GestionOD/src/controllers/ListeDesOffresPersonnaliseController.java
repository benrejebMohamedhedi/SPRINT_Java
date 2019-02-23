/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Demande;
import Entities.Prestation;
import com.jfoenix.controls.JFXTextField;
import connexion.conDB;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javax.swing.JTextField;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeDesOffresPersonnaliseController implements Initializable {
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    Connection cnxC;
    @FXML
    private TextField text_id;
    @FXML
    private TextField id_pres;
    @FXML
    private TextField date_rdvP;
    @FXML 
    private TextField id_user;
   
  public ListeDesOffresPersonnaliseController () throws SQLException {
       cnxC = conDB.getInstance().getCnx();
    }
   
    @FXML
         private ListView<Demande> table;
     private int selectIndex;
    @FXML
    private Button Accepter;
    @FXML
    private Button Refuser;
   
    
    /**
     * Initializes the controller class.
     */
    public void getAll(ActionEvent event) throws SQLException{
      DemandeServices ds = new DemandeServices();
            
            
            table.getItems().setAll(ds.findAll());
            table.setCellFactory(lv -> new ListOffres());
            
                Demande d =table.getSelectionModel().getSelectedItem();
                selectIndex=table.getSelectionModel().getSelectedIndex();
              
//                text_id.setText(d.getId_demande()+"");
                    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            DemandeServices ds = new DemandeServices();
            
            
            table.getItems().setAll(ds.findAll());
            table.setCellFactory(lv -> new ListOffres());
            table.setOnMouseClicked(event->{
                Demande d =table.getSelectionModel().getSelectedItem();
                selectIndex=table.getSelectionModel().getSelectedIndex();
                id_pres.setText(d.getId_prestataire()+"");
                text_id.setText(d.getId_demande()+"");
                date_rdvP.setText(d.getDate_rdv()+"");
                id_user.setText(d.getId_user()+"");
            });
        } catch (SQLException ex) {
            Logger.getLogger(ListeDesOffresPersonnaliseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
 
    @FXML
    private void AccepterDemande(ActionEvent event) throws SQLException {
          String idd = text_id.getText();
         int id_demande =Integer.parseInt(idd);
          String user = id_user.getText();
          int userr=Integer.parseInt(user);
         
           String prestataire = id_pres.getText();
          int Prestat =Integer.parseInt(prestataire);
          java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_rdvP.getText());
        DemandeServices Ds=new DemandeServices();
        Prestation P=new Prestation();
        Demande d=new Demande();
        P.setId_demande(id_demande);
        P.setDate_rdv(gettedDatePickerDate);
        P.setId_prestataire(Prestat);
        P.setId_user(userr);
      int status=  Ds.InsertPrestation(P);
      if(status>0){
          
        DemandeServices.deleteDemande(d);
          System.out.println("|||||");
      }
        
    }

    @FXML
    private void RefuserDemande(ActionEvent event) throws SQLException {
        
        String idd = text_id.getText();
         int id_demande =Integer.parseInt(idd);
            Demande d=new Demande();
           
            d.setId_demande(id_demande);
            int status=DemandeServices.deleteDemande(d);
        if(status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Demande");
            alert.setHeaderText("INFORMATIONS");
            alert.setContentText("Suppression avec succés !!");
            alert.showAndWait();
            DemandeServices ds =new DemandeServices();
            
         
        }
        else{
            
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("  Demande ");
            alert.setHeaderText("INFORMATIONS");
            alert.setContentText("Erreur !!");
            alert.showAndWait();
        }
        getAll(event);
    }
    }    
    

