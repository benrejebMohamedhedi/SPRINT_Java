/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Commande;
import Entities.Produit;
import Services.Commande.CommandeService;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import serviceLigneCom.Ligne_ComService;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class DivpanierController implements Initializable {

    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label id;
    @FXML
    private Rectangle rectangle;
   
    Connection cnx;
    @FXML
    private Label date;
    @FXML
    private Label qut;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @throws java.sql.SQLException
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }    
    public void loadValues(Produit e,int d) throws SQLException
    { 
        CommandeService dd = new CommandeService();
        Ligne_ComService s= new Ligne_ComService();
        nom.setText(e.getNom());
        System.out.println(e);
        
     //   etoile.setRating(Double.valueOf(e.getEtoile()));

        prix.setText(String.valueOf(e.getPrix())+" DT");
        id.setText(String.valueOf(e.getId_produit()));
        date.setText(""+dd.getDate(d));
        qut.setText(""+s.getquantite(e.getId_produit()));
        
       

        

       
//        sq.setPadding(new Insets(-10, -10, -10, -10));

       //        circle.setFill(new ImagePattern(imageURI));
//       qualite.setRating(e.getMoyqualite());
        Image imageURI2 = new Image("file:C://wamp64/www/GestionE_Shop/uploads/" + e.getImage().toString());
        rectangle.setFill(new ImagePattern(imageURI2));
      
    }
    
}
