
package vue;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.AffichageAjout;
import entities.service;
import java.io.IOException;
import java.net.URL;
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
 * FXML Controller class
 *
 * @author karim
 */
public class ListServiceController implements Initializable {

    @FXML
    private JFXTextField recherchetext;
    @FXML
    private ScrollPane pane;
    @FXML
    private JFXComboBox<?> tri;
 private ObservableList<service> data;
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            AffichageAjout Lp = new AffichageAjout();
          
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            FadeTransition ft = new FadeTransition(Duration.millis(1500));
            
            data =FXCollections.observableArrayList( Lp.afficherService());
            for ( service d : data) {
                
                try {
                    
                    
                    
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("divservice.fxml"));
                    Parent root = (Pane) loader.load();
                    DivserviceController DHC = loader.getController();
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
            Logger.getLogger(ListServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
    }    

    @FXML
    private void RechercheDynamique(KeyEvent event) throws SQLException {
         AffichageAjout LDS = new AffichageAjout();
         data=FXCollections.observableArrayList(LDS.afficherService());
        FilteredList<service> filteredData = new FilteredList<>(data, e -> true);
        recherchetext.setOnKeyReleased(e
                -> {
            recherchetext.textProperty().addListener((ObservableValue<? extends String> ObservableValue, String oldValue, String newValue) -> {
                filteredData.setPredicate((Predicate<? super service>) new Predicate<service>() {
                    @Override
                    public boolean test(service d) {
                        if (newValue == null || newValue.isEmpty()) {
                            
                            return true;
                            
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        return d.getCategorie().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                         //return d.getCategorie().toLowerCase().contains(lowerCaseFilter.subSequence(0, lowerCaseFilter.length()));
                    }
                });
            });
            TilePane b = new TilePane();
            b.setPadding(new javafx.geometry.Insets(30));
            TilePane c = new TilePane();
            for ( service d : filteredData) {
                
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("divservice.fxml"));
                    Parent root = (Pane) loader.load();
                    DivserviceController DHC = loader.getController();
                    DHC.LoadValues(d);
                    
                    //   c.setVgap(40);
                    c.getChildren().removeAll();
                    
                    
                    c.getChildren().add(root);
                } catch (IOException ex) {
                    Logger.getLogger(ListServiceController.class.getName()).log(Level.SEVERE, null, ex);
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
  
