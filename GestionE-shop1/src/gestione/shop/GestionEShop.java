/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nizar
 */
public class GestionEShop extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("DivProduit.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource(".fxml"));
       Parent root = FXMLLoader.load(getClass().getResource("affichageProduit.fxml"));

        
        Scene scene = new Scene(root);
      
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
