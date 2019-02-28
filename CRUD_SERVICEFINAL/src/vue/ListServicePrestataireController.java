/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import connexion.conDB;
import controller.AffichageAjout;
import controller.GestionAjouter;
import entities.prestataire;
import entities.service;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

/**
 *
 * @author karim
 */
public class ListServicePrestataireController implements Initializable{
Connection cnxC;
     public ListServicePrestataireController () throws SQLException {
       cnxC = conDB.getInstance().getCnx();
    }
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXComboBox<?> tri;
 private ObservableList<prestataire> data;
 
 @Override
    public void initialize(URL url, ResourceBundle rb) {
      
 
 
 
    }
 public void Reviewslist(prestataire p) throws SQLException {
 
  
        
    }/*
  public void RestoProfil(int id) throws SQLException {

       

        GestionAjouter gr = new GestionAjouter();

        tab.getSelectionModel().select(0);
        Connection cn = cnxC.getInstance().getConnection();

        ListEtablissement s = new ListEtablissement();

        Etablissement etab = s.ListBeaute().filtered(e -> e.getId() == id).get(0);
        if (PIDEV.Views.FirstFrame.user != null) {
            if (gr.DejaNote(user, etab) > 0) {
                dejanote.setVisible(true);
                panecom.setVisible(false);
            }
        }
 */

  public void ListPrest(service s) {
      
        
         try {
            
           // prestataire p =new prestataire();
            AffichageAjout Lp = new AffichageAjout();
          
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            
            data =FXCollections.observableArrayList( Lp.afficherPrestataire(s.getId_service()));
             System.out.println(data);
            for ( prestataire d : data) {
                
                try {
                    
                    
                    System.out.println("hjhjhjhjhjhjhjhj");
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("divservicePrestataire.fxml"));
                    Parent root = (Pane) loader.load();
                    divListServicePrestataire DHC = loader.getController();
                    DHC.LoadValues(d);
                    
                    //   c.setVgap(40);
                    c.getChildren().removeAll();
                    
                    
                    c.getChildren().add(root);
                } catch (IOException ex) {
                   // Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
       

        
        }
        catch (SQLException ex) {
            Logger.getLogger(ListServicePrestataireController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
        
    }
 }
