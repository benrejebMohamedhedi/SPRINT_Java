package gestione.shop;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;


import Entities.Produit;
import Service.CommandeService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import static org.apache.commons.lang3.time.FastDateParserSDFTest.data;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PanierController implements Initializable {

    @FXML
    private JFXTextField recherchetext;
    @FXML
    private ScrollPane pane;
     private ObservableList<Produit> data;
    @FXML
    private FontAwesomeIconView goBack;
    @FXML
    private Label total;
    @FXML
    private Button payment;
    @FXML
    private Button facture;

    /**
     * Initializes the controller class.
     */
      
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        
        try {
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            
            CommandeService ps= new CommandeService();
            total.setText("Prix Totale = "+ps.getPrixTotale()+"Dt");
               
            data = ps.loadPanier();
            for ( Produit d : data) {
                System.out.println("d"+d);
               
                try {
                   
                    for (int i :ps.getIdpanier())
                    {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Divpanier.fxml"));
                    Parent root = (Pane) loader.load();
                    DivpanierController DHC = loader.getController();
                
                    DHC.loadValues(d,i);
                    
                    //   c.setVgap(40);
                    c.getChildren().removeAll();
                    
                    
                    c.getChildren().add(root);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            c.setPrefColumns(3);
            c.setPadding(new javafx.geometry.Insets(0));
            c.setHgap(10);
            c.setVgap(80);
            b.getChildren().add(c);
            b.setPrefWidth(1000);
            pane.setContent(b);
             facture.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    try {
                        doubleclick(event,ps.getIdpanieru(1));
                    } catch (SQLException ex) {
                        Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

               
        });
                    
                    
                }catch (SQLException ex) {
                    Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                }
//            ////////// end 9dima////////////
//
////        editicon.setVisible(false);
////        deleteicon.setVisible(false);
//            ////////// end 9dima////////////
//
////        editicon.setVisible(false);
////        deleteicon.setVisible(false);
//            ////////// end 9dima////////////
//
////        editicon.setVisible(false);
////        deleteicon.setVisible(false);
//            ////////// end 9dima////////////
//
////        editicon.setVisible(false);
////        deleteicon.setVisible(false);


    }
 private void doubleclick(MouseEvent event, int idp) throws SQLException {
                     if (event.getClickCount() == 2) {
            try {
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Factures.fxml"));
                Parent root = loader.load();
                FacturesController DDC = loader.getController();
               
                DDC.Lafacture(idp);
               
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
    @FXML
    private void RechercheDynamique(KeyEvent event) throws SQLException {
                 CommandeService ps= new CommandeService();
                data=ps.loadPanier();
         FilteredList<Produit> filteredData = new FilteredList<>(data, e -> true);
        recherchetext.setOnKeyReleased(e
                -> {
            recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super Produit>) new Predicate<Produit>() {
                    @Override
                    public boolean test(Produit d) {
                        if (newValue == null || newValue.isEmpty()) {
                            
                            return true;

                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        return d.getNom().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                    }
                });
            });
            TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

        for ( Produit d : filteredData) {
            try {
                for (int i : ps.getIdpanier())
                {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DivPanier.fxml"));
                Parent root = (Pane) loader.load();
                DivpanierController DHC = loader.getController();
                
                DHC.loadValues(d,i);
                
                //   c.setVgap(40);
                c.getChildren().removeAll();
                
                
                c.getChildren().add(root);
                }
            } catch (IOException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
            }   catch (SQLException ex) {
                Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
    });
                }


    
    public void affichetrie(String scvalue, URL url, ResourceBundle rb, String trisq) throws SQLException {

        

    }

    public void trix(String trisq, URL url, ResourceBundle rb) {

      
    
        
    }    

    @FXML
    private void back(MouseEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
               goBack.getScene().setRoot(root);
    }

    @FXML
    private void PasserAuPayment(ActionEvent event) {

    }


  
     

   
    
}
