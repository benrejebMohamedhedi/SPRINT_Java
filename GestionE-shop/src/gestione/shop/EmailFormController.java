/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Facture;
import Service.FactureService;
import Service.SendEmail;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class EmailFormController implements Initializable {

    @FXML
    private TextField sendto;
    @FXML
    private TextField subj;
    @FXML
    private TextArea body;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void sendemail(int idp) throws SQLException {
       
    }
    
}
