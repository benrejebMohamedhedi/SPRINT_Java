/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Facture;
import Entities.Produit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class DivFactureController implements Initializable {

    @FXML
    private Label nomP;
    @FXML
    private Label refP;
    @FXML
    private Label qt;
    @FXML
    private Label prix;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void loadFacture(Produit p,int qu)   
{
    nomP.setText(p.getNom());
    refP.setText(p.getReference());
    qt.setText(qu+"");
    prix.setText(p.getPrix()+"Dt");
}
    
}
