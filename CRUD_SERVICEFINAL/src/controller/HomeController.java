/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
// javax.mail.*;

/**
 *
 * @author karim
 */
public class HomeController implements Initializable{
    
    @FXML 
  private AnchorPane rootPane ; 
   @FXML
   public void ajouterMenuItem (ActionEvent event) throws IOException{
    AnchorPane pane =FXMLLoader.load(getClass().getResource("/vue/ajouter.fxml")); 
    rootPane.getChildren().setAll(pane);
    
   }
   public void modifierMenuItem (ActionEvent event) throws IOException{
    AnchorPane pane =FXMLLoader.load(getClass().getResource("/vue/modifier.fxml")); 
    rootPane.getChildren().setAll(pane);
   }
   public void afficherserviceFrontItem (ActionEvent event) throws IOException{
    AnchorPane pane =FXMLLoader.load(getClass().getResource("/vue/ListService.fxml")); 
    rootPane.getChildren().setAll(pane);
   }
    @FXML 
     public void quitter(ActionEvent event){
        
        System.exit(0);
        
    }   
    
 @Override
    public void initialize(URL url, ResourceBundle rb) {
      
   
        
    }    
}
