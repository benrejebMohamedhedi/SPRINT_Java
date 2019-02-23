/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Produit;
import Services.Produit.ProduitService;
import Services.Produit.ProduitService;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class ModifierPController implements Initializable {

    @FXML
    private Button modifer;
    @FXML
    private TextArea desc;
    @FXML
    private TextField img;
    @FXML
    private TextField quant;
    @FXML
    private TextField prix;
    @FXML
    private TextField cat;
    @FXML
    private TextField ref;
    @FXML
    private TextField nom;
    
    @FXML
    private TextField search;
    @FXML
    private Button ser;
    @FXML
    private Button suppr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
   
    }
    @FXML 
    public void modifier(ActionEvent even) throws SQLException, IOException
    {
        ProduitService ps = new ProduitService();
         Produit ppa =new Produit();
           ppa.setNom(nom.getText());
           ppa.setQuantite(Integer.parseInt(quant.getText()));
           ppa.setCateorie(cat.getText());
           ppa.setPrix(Integer.parseInt(prix.getText()));
           ppa.setReference(ref.getText());
           ppa.setDescription(desc.getText());
           ppa.setImage(img.getText());
           ps.modifierProduit(ppa);
            Parent root= FXMLLoader.load(getClass().getResource("affichageProduit.fxml"));
               suppr.getScene().setRoot(root);
    }
    @FXML
   public void searchby (ActionEvent event) throws SQLException
        {
            Produit p = new Produit();
 
            ProduitService ps = new ProduitService();
            String reference= search.getText();
           // System.out.println();
            Produit pp= ps.getBysh(reference);
           // System.out.println(pp);
            nom.setText(pp.getNom());
            ref.setText(pp.getReference());
            quant.setText(pp.getQuantite()+"");
            prix.setText(pp.getPrix()+"");
            cat.setText(pp.getCateorie());
            desc.setText(pp.getDescription());
            img.setText(pp.getImage());
        /* catch (SQLException ex) {
            Logger.getLogger(ModifierPController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
             
                }
   @FXML 
   public void supprimer (ActionEvent ev) throws SQLException, IOException
   {
                   ProduitService ps = new ProduitService();
                   String ref=this.ref.getText();
                   ps.supprimerPrd(ref);
                    Parent root= FXMLLoader.load(getClass().getResource("affichageProduit.fxml"));
               suppr.getScene().setRoot(root);

       
       
   }

        }
    
    

