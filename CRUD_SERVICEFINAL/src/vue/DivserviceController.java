/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import entities.service;
import java.io.IOException;
import java.net.URL;
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
import vue.divListServicePrestataire;
import controller.HomeController;
import entities.prestataire;

/**
 * FXML Controller class
 *
 * @author karim
 */
public class DivserviceController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Label nom;
    
  @FXML
    private Label sous_categorie;
    @FXML
    private Label id;
    @FXML
    private Rectangle rectangle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void LoadValues(service e) throws IOException {
    
        nom.setText(e.getCategorie());
        sous_categorie.setText(e.getSous_categorie());
        
        
     //   etoile.setRating(Double.valueOf(e.getEtoile()));

        
        id.setText(String.valueOf(e.getId_service()));
        
       

        

       
//        sq.setPadding(new Insets(-10, -10, -10, -10));

       //        circle.setFill(new ImagePattern(imageURI));
//       qualite.setRating(e.getMoyqualite());
        Image imageURI2 = new Image("file:C://wamp/www/gestionservice/image/" + e.getImage().toString());
        rectangle.setFill(new ImagePattern(imageURI2));
      
        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        doubleclick(event,e);
                    } catch (SQLException ex) {
                        Logger.getLogger(DivserviceController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
    

      }
        public void doubleclick(MouseEvent event,service e) throws SQLException {
           if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/listServicePrestataire.fxml"));
                 Parent root = loader.load();
              /* Parent root = loader.load();
                ProfilBeauteController DDC = loader.getController();
                DDC.Reviewslist(selectedetab);
                DDC.RestoProfil(selectedetab.getId());*/
                ListServicePrestataireController DDC = loader.getController();
                DDC.ListPrest(e);
                
               // DDC.Reviewslist(selectedetab.getId_service());
              // divListServicePrestataire DDC = loader.getController();
           //  DDC.Reviewslist(selectedetab);
              //  DDC.RestoProfil(selectedetab.getId());
                
                FXMLLoader loade = new FXMLLoader(getClass().getResource("/vue/home.fxml"));
                Parent roo = loade.load();
                HomeController Dhp = loade.getController();
                Stage ss=new Stage();
                Scene sc = new Scene(root);
                ss.setScene(sc);
                ss.setWidth(1288);
                ss.setHeight(750);
                
                
                ss.show();

            } catch (IOException ex) {
                Logger.getLogger( divListServicePrestataire.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
    
    
    
}
