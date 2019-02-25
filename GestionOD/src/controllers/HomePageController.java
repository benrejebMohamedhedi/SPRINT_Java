/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class HomePageController implements Initializable {

    /**
     * Initializes the controller class.
     */
       @FXML 
  private AnchorPane rootPane ; 
   @FXML
   public void ListeMenuItem (ActionEvent event) throws IOException{
    AnchorPane pane =FXMLLoader.load(getClass().getResource("/vues/ListeDemandes.fxml")); 
    rootPane.getChildren().setAll(pane);
    
   }
       @FXML
   public void ajouterMenuItem (ActionEvent event) throws IOException{
    AnchorPane pane =FXMLLoader.load(getClass().getResource("/vues/AjouterDemande.fxml")); 
    rootPane.getChildren().setAll(pane);
   }
   public void ajouterMenuItem1 (ActionEvent event) throws IOException{
    AnchorPane pane =FXMLLoader.load(getClass().getResource("/vues/AjouterDemandePersonnalise.fxml")); 
    rootPane.getChildren().setAll(pane);
   }
   public void ajouterMenuItem2 (ActionEvent event) throws IOException{
    AnchorPane pane =FXMLLoader.load(getClass().getResource("/vues/ListeDesOffresPersonnalise.fxml")); 
    rootPane.getChildren().setAll(pane);
   }
    @FXML 
     public void quitter(ActionEvent event){
        
        System.exit(0);
        
    }  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
