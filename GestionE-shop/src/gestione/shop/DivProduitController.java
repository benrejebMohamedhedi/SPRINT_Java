/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Produit;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
//import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class DivProduitController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prix;
    @FXML
    private Label id;
  //  private Rating etoile;
    @FXML
    private AnchorPane ap;
    @FXML
    private Pane sq;
    @FXML
    private Rectangle rectangle;
    @FXML
    private JFXListView<?> items;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }
public void LoadValues(Produit e) throws IOException {
    
        nom.setText(e.getNom());
        
        
     //   etoile.setRating(Double.valueOf(e.getEtoile()));

        prix.setText(String.valueOf(e.getPrix())+" DT");
        id.setText(String.valueOf(e.getId_produit()));
        
       

        

       
//        sq.setPadding(new Insets(-10, -10, -10, -10));

       //        circle.setFill(new ImagePattern(imageURI));
//       qualite.setRating(e.getMoyqualite());
        Image imageURI2 = new Image("file:C://wamp64/www/GestionE_Shop/uploads/" + e.getImage().toString());
        rectangle.setFill(new ImagePattern(imageURI2));
      
        ap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    doubleclick(event, e);
                }

            }
        });
    

      }
        public void doubleclick(MouseEvent event, Produit selectedetab) {
        if (event.getClickCount() == 2) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfilProduit.fxml"));
                Parent root = loader.load();
                ProfilProduitController DDC = loader.getController();
                DDC.Reviewslist(selectedetab);
                DDC.ProfilProduit(selectedetab.getId_produit());
                FXMLLoader loade = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                Parent roo = loade.load();
                HomePageController Dhp = loade.getController();
                Stage ss=new Stage();
                Scene sc = new Scene(root);
                ss.setScene(sc);
                ss.setWidth(1288);
                ss.setHeight(750);
                
                
                ss.show();

            } catch (IOException | SQLException ex) {
                Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
    
}
