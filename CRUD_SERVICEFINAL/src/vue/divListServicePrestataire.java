/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import entities.prestataire;
import entities.service;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author karim
 */
public class divListServicePrestataire implements Initializable {

  
    @FXML
    private Pane sq;
    @FXML
    private Label nom;
    
  @FXML
    private Label prenom;
    @FXML
    private Label id;
    @FXML
    private Rectangle rectangle;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {}
        
public void LoadValues(prestataire p){
         //nom.setText(e.getCategorie());
         nom.setText(p.getNom());
         prenom.setText(p.getPrenom());
      //  sous_categorie.setText(e.getSous_categorie());
        
        
     //   etoile.setRating(Double.valueOf(e.getEtoile()));

        
        //id.setText(String.valueOf(e.getId_service()));
        
        id.setText(String.valueOf(p.getId_service()));
        
       
        

       
//        sq.setPadding(new Insets(-10, -10, -10, -10));

       //        circle.setFill(new ImagePattern(imageURI));
//       qualite.setRating(e.getMoyqualite());
System.out.println("hhhhh");
        Image imageURI2 = new Image("file:C://wamp/www/gestionservice/image/" + p.getPhoto_profil());
        rectangle.setFill(new ImagePattern(imageURI2));
      
    }

}