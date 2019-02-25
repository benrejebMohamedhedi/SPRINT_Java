/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Produit;
import Service.CommandeService;
import Service.FactureService;
import Service.Ligne_ComService;
import Service.pdf;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class FacturesController implements Initializable {

    @FXML
    private ScrollPane sp;
    @FXML
    private Label tot;
    @FXML
    private Button topdf;
    @FXML
    private Button email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void Lafacture(int idp) throws SQLException {
        System.out.println(""+idp);
         TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
        CommandeService cs= new CommandeService();
        Ligne_ComService lc= new Ligne_ComService();
        tot.setText(cs.getPrixTotale()+"Dt");
            for ( Produit d :cs.loadPanier()) {
                
                try {
                    
                    
                    
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DivFacture.fxml"));
                    Parent root = (Pane) loader.load();
                    DivFactureController DHC = loader.getController();
                    DHC.loadFacture(d,lc.getquantite(d.getId_produit()));
                    
                    //   c.setVgap(40);
                    c.getChildren().removeAll();
                    
                    
                    c.getChildren().add(root);
                } catch (IOException ex) {
                   // Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
         c.setPrefColumns(1);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(2);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        sp.setContent(b);
          FactureService fs= new FactureService();
       fs.ajouterfacture(idp);
        System.out.println("="+fs.getFacturebyid(idp).getId_facture());
          topdf.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    try {
                        doubleclick(event,fs.getFacturebyid(idp).getId_facture());
                    } catch (SQLException ex) {
                        Logger.getLogger(FacturesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

               
        });
           email.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 1) {
                    try {
                        doubleclickk(event,fs.getFacturebyid(idp).getId_facture());
                    } catch (SQLException ex) {
                        Logger.getLogger(FacturesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

               
        });
       
       
        
        
        
        
        




    }
    private void doubleclick(MouseEvent event, int idp) throws SQLException {
                     if (event.getClickCount() == 1) {
           pdf p= new pdf();
           p.creation(idp);
        }
                }
      private void doubleclickk(MouseEvent event, int idp) throws SQLException {
                     if (event.getClickCount() == 1) {
                         try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EmailForm.fxml"));
                Parent root = loader.load();
                EmailFormController DDC = loader.getController();
               
                DDC.sendemail(idp);
               
                Stage ss=new Stage();
                Scene sc = new Scene(root);
                ss.setScene(sc);
                ss.setWidth(1288);
                ss.setHeight(750);
                
                
                ss.show();

            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        }
                }
    
    
}
