/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Produit;
import Service.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
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
import javafx.event.ActionEvent;
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
 * FXML Controller class
 *
 * @author nizar
 */
public class ListProduitController implements Initializable {

    @FXML
    private JFXTextField recherchetext;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXComboBox<String> tri;
     private ObservableList<Produit> data;
    @FXML
    private JFXButton gotopanier;
private AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ProduitService Lp = new ProduitService();
            tri.getItems().addAll("Meilleur Prix");
            tri.getItems().addAll("Meilleur rating");
            tri.getItems().addAll("Default order");
            tri.setValue("Default order");
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            
            data =FXCollections.observableArrayList( Lp.getAllProduit());
            for ( Produit d : data) {
                
                try {
                    
                    
                    
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DivProduit.fxml"));
                    Parent root = (Pane) loader.load();
                    DivProduitController DHC = loader.getController();
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
        tri.setOnAction((event) -> {
           

            String abc = tri.getValue();
             if (("Default order").equals(abc)) {

               
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                    Parent root = loader.load();
                    HomePageController pu = loader.getController();
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
                    pu.setNode(Rev);
                   // recherchetext.getScene().setRoot(root);
                   
                } catch (IOException ex) {
                    Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
             
            else if ("Default order".equals(abc))
                    {
                 try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                    Parent root = loader.load();
                    HomePageController pu = loader.getController();
                    AnchorPane Rev = FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
                    pu.setNode(Rev);
                    
                    recherchetext.getScene().setRoot(root);
                   
                } catch (IOException ex) {
                    Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                    }


         /*  else if (!( "Default order".equals(abc)) 
                { trix( "id", url, rb);}
                
            else
            {   try {
                affichetrie(scvalue, url, rb, abc);
                } catch (SQLException ex) {
                    Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
                }
}*/
            
        });
        
        
             
        
        
        }
        catch (SQLException ex) {
            Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }  
    @FXML 
    private void TOpanier(ActionEvent event) throws IOException
{
    Parent root= FXMLLoader.load(getClass().getResource("panier.fxml"));
               gotopanier.getScene().setRoot(root);
}
    @FXML
    private void RechercheDynamique(KeyEvent event) throws SQLException {
         ProduitService LDS = new ProduitService();
         data=FXCollections.observableArrayList(LDS.getAllProduit());
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
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DivProduit.fxml"));
                    Parent root = (Pane) loader.load();
                    DivProduitController DHC = loader.getController();
                    DHC.LoadValues(d);
                    
                    //   c.setVgap(40);
                    c.getChildren().removeAll();
                    
                    
                    c.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(ListProduitController.class.getName()).log(Level.SEVERE, null, ex);
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

    
     public void trix(String trisq, URL url, ResourceBundle rb) {

        /*ProduitService le = new ProduitService();
        data = le.TriserviceHotel(trisq);
        TilePane b = new TilePane();
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

         for ( Etablissement d : data) {
             System.out.println(d.getSouscat().getNom());
            
            try {
               
           
       
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/DivListHotel.fxml"));
                Parent root = (Pane) loader.load();
                DivListController DHC = loader.getController();
                
                DHC.LoadValues(d);
               
               
                //   c.setVgap(40);
                c.getChildren().removeAll();
               
                
                c.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ListrestoController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        c.setPrefColumns(3);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(10);
        c.setVgap(80);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        pane.setContent(b);
*/
    }
    
}
