/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Produit;
import Services.Produit.ProduitService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class FrontProduitController implements Initializable 
{

    private ObservableList<Image> listOfimages= FXCollections.observableArrayList();
    @FXML
    private ImageView image;
    @FXML
    private Rectangle rectangle;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        Image imageURI2 = new Image("file:C://Users/nizar/Documents/NetBeansProjects/GestionE-shop/src/gestione/shop/uploads/60c9ac7333018941efd5657bc16df24e.jpg" );
        rectangle.setFill(new ImagePattern(imageURI2));
        
       /* int j =0;
        try {
            ProduitService ps= new ProduitService();
            for(String i : ps.getImages())
            {
               j++;
                listOfimages.add(new Image ("http://localhost/GestionE_Shop/uploads/"+i));
               
            }
           
            
            
            
            ListView<Produit> listView = new ListView<Produit>();
            ObservableList<Produit> items =FXCollections.observableArrayList ();
            for (Produit i : ps.getAllProduit())
            {
                items.add(i);
            }
                listView.setItems(items);
            
            listView.setCellFactory(param -> new ListCell<Produit>() {
                private ImageView imageView = new ImageView();
             
                public void updateItem(Produit name, boolean empty) {
                    super.updateItem(name, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        for (Image i : listOfimages)
                        {
                        if(i.impl_getUrl().equals("http://localhost/GestionE_Shop/uploads/"+name.getImage()))
                            imageView.setImage(i);
                        setText(name.getNom());
                        setGraphic(imageView);
                        }
                    }
                }
            });
            VBox box = new VBox(listView);
            box.setAlignment(Pos.CENTER);
            
        } catch (SQLException ex) {
                    Logger.getLogger(FrontProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
         
    }  
    
    */
    }
}
