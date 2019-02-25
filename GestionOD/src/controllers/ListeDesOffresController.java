/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Demande;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

//import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.DateCell;
import javafx.util.Callback;
/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ListeDesOffresController implements Initializable {
    @FXML
    private ListView<Demande> table;
    private ObservableList<Demande> ListDemande ;
    private int selectIndex;
    @FXML
    private DatePicker search;
    /**
     * Initializes the controller class.
     */
   
   /* public static final LocalDate LOCAL_DATE (String dateString){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate localDate = LocalDate.parse(dateString);
    return localDate;
}*/
    
    @FXML public void getAll(ActionEvent event) throws SQLException{
      DemandeServices ds = new DemandeServices();
            
            
            table.getItems().setAll(ds.findAll());
            table.setCellFactory(lv -> new ListOffres());
            
                Demande d =table.getSelectionModel().getSelectedItem();
                selectIndex=table.getSelectionModel().getSelectedIndex();
              
//                text_id.setText(d.getId_demande()+"");
                    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      
          try {
            DemandeServices qs = new DemandeServices();
       

            table.getItems().setAll(qs.fillOffre());
            table.setCellFactory(lv -> new ListOffres());
            table.setOnMouseClicked(event->{
                 Demande d =table.getSelectionModel().getSelectedItem();
                selectIndex=table.getSelectionModel().getSelectedIndex();
               
            });
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ListeDemandesController.class.getName()).log(Level.SEVERE, null, ex);
        }
//          String date = search.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    } 
    
}
