/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_user;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import Entity.*;
import Services.Service_Client;
import Services.Service_Prestataire;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NacimGastli
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button btnSignin;
    @FXML
    private Label btnForgot;
    @FXML
    private Label lblErrors;
    @FXML
    private Label lblSuccess;
    @FXML
    private Button btnInscription;
    UserSession US;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        US = UserSession.getInstace();
        System.out.println(US);
        SendMail s = new SendMail();
        int code = s.GenerateCode();
        System.out.println(code);

    }

    public static boolean showInscription() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(LoginController.class.getResource("/gui_user/Inscription.fxml"));
        AnchorPane page = (AnchorPane) loader.load();
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Remplissez le formulaire d'inscription");
        dialogStage.initModality(Modality.WINDOW_MODAL);

        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        dialogStage.showAndWait();
        return false;
    }

    @FXML
    public void handleInscription() throws IOException {
        LoginController.showInscription();
    }

    @FXML
    public void Signin(ActionEvent event) throws IOException, SQLException, InterruptedException {
        int verif = 0;
        int verif1 = 0;

        String email = txtUsername.getText();
        String password = txtPassword.getText();
        Client u = new Client(email, password);
        u.setEmail(email);
        u.setPassword(password);
        Service_Client ss = new Service_Client();

        Prestataire C = new Prestataire(email, password);
        C.setEmail(email);
        C.setPassword(password);
        Service_Prestataire sp = new Service_Prestataire();

        verif1 = sp.SignIn(C);
        verif = ss.SignIn(u);
        
        if (verif1 == 1) {

            TextInputDialog dialog = new TextInputDialog("Votre code");
            dialog.setTitle("Code de vérification");
            dialog.setHeaderText("Veuillez insérer le code\nqui était envoyé sur votre boite mail ");
            dialog.setContentText("Please enter your code:");
            Optional<String> result = dialog.showAndWait();
            verif1 = 0;
            verif = 0;

            if (result.isPresent()) {
                System.out.println("Your code: " + result.get());
                if (sp.VerifierCompte(C, result.get()) == 1) {
                    Alert A1 = new Alert(Alert.AlertType.CONFIRMATION);
                    A1.setTitle("Compte activé");
                    A1.setHeaderText("Compte activé");
                    A1.setContentText("Veuillez saisir vos coordonnés pour vous connecter");
                    A1.showAndWait();
                    dialog.close();
                }

            }
        } else if (verif == 1) {

            TextInputDialog dialog = new TextInputDialog("Votre code");

            dialog.setTitle("Code de vérification");
            dialog.setHeaderText("Veuillez insérer le code\nqui était envoyé sur votre boite mail ");
            dialog.setContentText("Please enter your code:");
            Optional<String> result = dialog.showAndWait();
            verif1 = 0;
            verif = 0;

            if (result.isPresent()) {
                System.out.println("Your code: " + result.get());
                if (ss.VerifierCompte(u, result.get()) == 1) {
                    Alert A1 = new Alert(Alert.AlertType.CONFIRMATION);
                    A1.setTitle("Compte activé");
                    A1.setHeaderText("Compte activé");
                    A1.setContentText("Veuillez saisir vos coordonnés pour vous connecter");
                    A1.showAndWait();
                    dialog.close();
                }

            }

        } else if (verif1 == 2 || verif == 2) {
            Alert A1 = new Alert(Alert.AlertType.ERROR);
            A1.setTitle("Compte désactivé");
            A1.setHeaderText("Veuillez activer votre compte");
            A1.showAndWait();
            verif1 = 0;
            verif = 0;

        } else if (verif1 == 3) {
            String nom = "";
            String prenom = "";
            int id = 0;
            US = UserSession.getInstace(nom, prenom, id);
            US.setNom(C.Session(C).getNom());
            US.setPrenom(C.Session(C).getPrenom());
            US.setId(C.Session(C).getid());
            //System.out.println(US);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui_user/Acceuil_Prestataire.fxml")));
            stage.setScene(scene);
            stage.show();
        } else if (verif == 3) {
            lblErrors.setText("");
            lblSuccess.setTextFill(Color.GREEN);
            lblSuccess.setText("Login Successful..Redirecting..");
//          Thread.sleep(3000);          
            System.out.println("Successfull Login");
            String nom = "";
            String prenom = "";
            int id = 0;
            US = UserSession.getInstace(nom, prenom, id);
            US.setNom(u.Session(u).getNom());
            US.setPrenom(u.Session(u).getPrenom());
            US.setId(u.Session(u).getid());
            System.out.println(US);
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui_user/Acceuil_Client.fxml")));
            stage.setScene(scene);
            stage.show();
        } //      Parent root = FXMLLoader.load(getClass().getResource("/gui_user/AcceuilClient.fxml"));
        //      btnSignin.getScene().setRoot(root);
        else {
            lblErrors.setTextFill(Color.TOMATO);
            lblErrors.setText("Enter Correct Email/Password");
            System.err.println("Wrong Logins --///");
        }

    }

    @FXML
    private void handle(MouseEvent event) {
    }

}
