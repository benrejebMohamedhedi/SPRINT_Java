/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entity.Client;
import Entity.Prestataire;
import Entity.SendMail;
import Entity.UserSession;
import Services.Service_Client;
import Services.Service_Prestataire;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NacimGastli
 */
public class Acceuil_PrestataireController implements Initializable {

    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtNum;
    @FXML
    private Label lblWelcome;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnDesactiver;
    @FXML
    private Button btnUpload;
    @FXML
    private TextArea txtAdresse;
    @FXML
    private TextField txtUsername;
    @FXML
    private Button btnLogOut;
    UserSession us;

    /**
     * Initializes the controller class.
     **/
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // TODO
        Prestataire R = new Prestataire();
        us = UserSession.getInstace();
        System.out.println(us);
        int id_user = us.getId();
        lblWelcome.setText("Welcome " + us.getNom() + " " + us.getPrenom());

        Afficher();
    }

    public Prestataire Chercher(int id_user) {
        
        Prestataire R = new Prestataire();
        Service_Prestataire sc = new Service_Prestataire();
        R = sc.chercher(id_user);
        return R;
    }

    public void Afficher() {
        Prestataire R = new Prestataire();
        R = Chercher(us.getId());
        txtNom.setText(R.getNom());
        txtPrenom.setText(R.getPrenom());
        txtEmail.setText(R.getEmail());
        txtNum.setText(R.getNum_tel());
        txtUsername.setText(R.getUsername());
        txtAdresse.setText(R.getAdresse());

    }

    @FXML
    public void Modifier(ActionEvent event) {

        String email = txtEmail.getText();
        String username = txtUsername.getText();
        String prenom = txtPrenom.getText();
        String nom = txtNom.getText();
        String adresse = txtAdresse.getText();
        String num_tel = txtNum.getText();
        String photo_profil = "";
        SendMail sm = new SendMail();
        Prestataire P = new Prestataire(us.getId(), email, username, prenom, nom, adresse, num_tel, photo_profil);
        Service_Prestataire ss = new Service_Prestataire();
        int i = 0;
        i = ss.update(P);
        System.out.println(i);
        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modifier Profil");
            alert.setHeaderText(null);
            alert.setContentText("Votre profil a été modifié");
            alert.showAndWait();
        }
    }

    @FXML
    public void Deconnexion(ActionEvent event) throws IOException {
        
        us = UserSession.UserSessionLogOut();
        Node node;
        node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui_user/LoginUser.fxml")));
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void Desactiver(ActionEvent event) {
        
        Service_Prestataire sc = new Service_Prestataire();
        sc.Delete(us.getId());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Désactiver Compte");
        alert.setHeaderText(null);
        alert.setContentText("Votre compte a été désactivé");
        alert.showAndWait();

    }

}
