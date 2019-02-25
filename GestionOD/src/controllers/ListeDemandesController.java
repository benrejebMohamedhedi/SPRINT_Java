/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Demande;
import connexion.conDB;
import static controllers.AjouterDemandePersonnaliseController.saveToFileImageNormal;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Callback;
import javafx.beans.value.ChangeListener;
import jdk.nashorn.internal.objects.Global;
import static org.apache.commons.lang3.time.FastDateParserSDFTest.data;
import static org.apache.commons.lang3.time.FastDatePrinterTimeZonesTest.data;
import static org.apache.xalan.lib.ExsltDatetime.date;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeDemandesController implements Initializable {
 /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    Connection cnxC;
    @FXML
    private DatePicker date_rdv;
     public ListeDemandesController () throws SQLException {
       cnxC = conDB.getInstance().getCnx();
    }
    private int selectIndex;
    @FXML
     private ListView<Demande> table;
     private ObservableList<Demande> ListDemande ;
    @FXML
     private TextField search;
     //private TextField filterInputs; 
    @FXML
    private TextArea txt_description;
    @FXML
    private TextField txt_budget;
    @FXML
    private TextField text_id;
    @FXML
    private Button modif;

   ListDemande l=new ListDemande();
/*public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString);
    return localDate;
}*/
   
    @FXML public void getAll(ActionEvent event) throws SQLException{
      DemandeServices ds = new DemandeServices();
            
            
            table.getItems().setAll(ds.findAll());
            table.setCellFactory(lv -> new ListDemande());
            
                Demande d =table.getSelectionModel().getSelectedItem();
                selectIndex=table.getSelectionModel().getSelectedIndex();
              
//                text_id.setText(d.getId_demande()+"");
                    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try {
            DemandeServices ds = new DemandeServices();
      

            table.getItems().setAll(ds.findAll());
            table.setCellFactory(lv -> new ListDemande());
            table.setOnMouseClicked(event->{
                 Demande d =table.getSelectionModel().getSelectedItem();
                selectIndex=table.getSelectionModel().getSelectedIndex();
                txt_description.setText(d.getDescription());
                txt_budget.setText(d.getPrix()+"");
                text_id.setText(d.getId_demande()+"");
                //java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date_rdv.getValue());
//                date_rdv.setValue(LOCAL_DATE("2"));
                
            });
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ListeDemandesController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
           
          search.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    try {
                        filterDemandes((String) oldValue, (String) newValue);
                    } catch (SQLException ex) {
                        Logger.getLogger(ListeDemandesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }

            
                
            });
            if (search.getText()== null)
            {
             try {
                 DemandeServices pss=new DemandeServices();
                 
                // ObservableList<Demande> obbb = FXCollections.observableArrayList(pss.findAll());
                 l.getDescription().setUserData(new PropertyValueFactory<>("Description"));
                 l.getPrix().setUserData(new PropertyValueFactory<>("prix"));
                 l.getDate_rdv().setUserData(new PropertyValueFactory<>("Date_rdv"));
                 
                 
// table.setCellFactory(lv -> new ListDemande());
             }
             //initialize editable attributes
             catch (SQLException ex) {
                 Logger.getLogger(ListeDemandesController.class.getName()).log(Level.SEVERE, null, ex);
             }
            }
            
            
           
            
            l.getDescription().setUserData(TextFieldTableCell.forTableColumn());
           
            
            
            
       
           
         
         
         
         
          
         
         
         
          }
                     
       @FXML
        private void DeleteDemande(ActionEvent event) throws SQLException {
          String idd = text_id.getText();
         int id_demande =Integer.parseInt(idd);
            Demande d=new Demande();
           // DemandeServices ds=new DemandeServices();
            d.setId_demande(id_demande);
            int status=DemandeServices.deleteDemande(d);
        if(status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Demande");
            alert.setHeaderText("INFORMATIONS");
            alert.setContentText("Suppression avec succés !!");
            alert.showAndWait();
         
        }
        else{
            
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Delete Demande");
            alert.setHeaderText("INFORMATIONS");
            alert.setContentText("Erreur !!");
            alert.showAndWait();
        }
        
        
     
         
        
           getAll(event);
        
           clearsfield();
        }
    
    @FXML
    private void UpdateDemande(ActionEvent event) throws SQLException {
     
         DemandeServices ds=new DemandeServices();
         
      java.sql.Date RDV = java.sql.Date.valueOf(date_rdv.getValue());
         String idd = text_id.getText();
         int id_demande =Integer.parseInt(idd);
         String description=txt_description.getText();
         String p =txt_budget.getText();
         int prix=Integer.parseInt(p);
         
        Demande d =new Demande();
        
         
        
         d.setId_demande(id_demande);
         d.setDescription(description);
         d.setPrix(prix);
        // d.setDate_rdv(RDV);
         d.setDate_rdv(RDV);
        int status=DemandeServices.update(d);
        if(status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Demande");
            alert.setHeaderText("INFORMATIONS");
            alert.setContentText("Modification avec succés !!");
            alert.showAndWait();
            
        }
        else{
            
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Update Demande");
            alert.setHeaderText("INFORMATIONS");
            alert.setContentText("Erreur !!");
            alert.showAndWait();
        }
        
        getAll(event);
        //clearsfield();
         
        
        
        
    }
     public void clearsfield(){
        txt_description.clear();
        txt_budget.clear();
    }
  
    
     public void filterDemandes(String oldValue, String newValue) throws SQLException {
         DemandeServices ps = new DemandeServices();
      
            ObservableList<Demande> demandes = FXCollections.observableArrayList(ps.findAll());
        ObservableList<Demande> filteredList = FXCollections.observableArrayList();
        if(search == null || (newValue.length() < oldValue.length()) || newValue == null) {
            table.setItems(demandes);
        }
        else {
            newValue = newValue.toUpperCase();
            for(Demande p : table.getItems()) {
                String description = p.getDescription();
         
                if(description.toUpperCase().contains(newValue)) {
                    filteredList.add(p);
                }
            }
            table.setItems(filteredList);
        }
    }
  
    }    
    
