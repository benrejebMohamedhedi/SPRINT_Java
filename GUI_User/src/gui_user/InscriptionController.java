/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_user;

import Entity.Client;
import Entity.Prestataire;
import Entity.SendMail;
import Services.Service_Client;
import Services.Service_Prestataire;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NacimGastli
 */
public class InscriptionController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private TextField txtCINP;
    @FXML
    private TextField txtAdresseP;
    @FXML
    private TextField txtNumP;
    @FXML
    private TextField txtUsernameP;
    @FXML
    private TextField txtEmailP;
    @FXML
    private TextField txtPrenomP;
    @FXML
    private TextField txtNomP;
    @FXML
    private PasswordField pwdP;
    @FXML
    private PasswordField pwdCP;
    @FXML
    private DatePicker dateP;

    @FXML
    private Button btnInscriptionP;
    @FXML
    private Label l3;
    @FXML
    private ComboBox<String> SexeP;
    @FXML
    private ComboBox<String> Specialite;
    @FXML
    private TextField txtNomC;
    @FXML
    private TextField txtPrenomC;
    @FXML
    private TextField txtEmailC;
    @FXML
    private TextField txtUsernameC;
    @FXML
    private PasswordField pwdC;
    @FXML
    private PasswordField pwdCC;
    @FXML
    private TextField txtCINC;
    @FXML
    private TextField txtNumC;
    @FXML
    private TextField txtAdresseC;
    @FXML
    private DatePicker dateC;
    @FXML
    private ComboBox<String> SexeC;
    @FXML
    private Label lblErr;
    @FXML
    private Tab btnInscriptionC;
    @FXML
    private Button btnInscriptionP1;
    @FXML
    private Label lblErr2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SexeP.getItems().addAll("Homme", "Femme");
        SexeP.getSelectionModel().select("Homme");
        Specialite.getItems().addAll("Menuisier", "Plombier", "Jardinier");
        Specialite.getSelectionModel().select("Menuisier");
        SexeC.getItems().addAll("Homme", "Femme");
        SexeC.getSelectionModel().select("Homme");

    }

    @FXML
    public void handlecancel(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

    }

    public boolean validateNomP() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txtNomP.getText());
        if (m.find() && m.group().equals(txtNomP.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateEmaillP() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(txtEmailP.getText());
        if (m.find() && m.group().equals(txtEmailP.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateCINP() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtCINP.getText());
        if (m.find() && m.group().equals(txtCINP.getText()) && txtCINP.getText().length() == 8) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate CIN");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid CIN");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validatePasswordP() {
        // Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
        // Matcher m = p.matcher(pwdC.getText());
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (pwdP.getText().matches(pattern)) {

            if (pwdCP.getText().equals(pwdCP.getText())) {
                return true;
            } else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate Password");
                alert.setHeaderText(null);
                alert.setContentText("Check your password confirmation");
                alert.showAndWait();
                return false;
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateDateP() {
        if (dateP.getValue() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;
        }

        Date date = java.sql.Date.valueOf(dateP.getValue());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        Calendar c = Calendar.getInstance();
        if (c.getTime().after(date) == true && c.getTime().equals(date) == false) {
            return true;

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;

        }
    }

    @FXML
    public void InscriptionP(ActionEvent event) throws SQLException {
        String email = txtEmailP.getText();
        String username = txtUsernameP.getText();
        String password = pwdP.getText();
        String passwordC = pwdCP.getText();
        String prenom = txtPrenomP.getText();
        String nom = txtNomP.getText();
        String cin = txtCINP.getText();
        String sexe = SexeP.getValue();
        LocalDate date_naissanceP = dateP.getValue();
        String adresse = txtAdresseP.getText();
        String num_tel = txtNumP.getText();
        String photo_profil;
        int status = 0;
        int role = 2;
        String Specialitee = Specialite.getValue();
        SendMail sm = new SendMail();
        Prestataire P = new Prestataire(Specialitee, email, username, password, prenom, nom, cin, sexe, date_naissanceP, adresse, num_tel, status, role);

        System.out.println(P);
        Service_Prestataire ss = new Service_Prestataire();
        if (validateDateP() & validateCINP() & validateNomP() & validateEmaillP() & validatePasswordP()) {

            if (ss.insert(P) == 0) {
                lblErr2.setTextFill(Color.TOMATO);
                lblErr2.setText("CIN déja existant !");
                System.err.println("Wrong inscription --///");
            } else {
                sm.SendEmail(email);
                /*JOptionPane.showMessageDialog(null, "Inscription reussie");*/
                Alert A1 = new Alert(Alert.AlertType.INFORMATION);
                A1.setTitle("Confirmation d'inscription");
                A1.setHeaderText("Inscription Réussie");
                A1.setContentText("Un mail de vérification a été envoyé, veuillez vérifier votre compte avant de vous connecter.");
                A1.showAndWait();

                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

            }
        }

    }

    public boolean validateNomC() {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher m = p.matcher(txtNomC.getText());
        if (m.find() && m.group().equals(txtNomC.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate First Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid First Name");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateEmaillC() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(txtEmailC.getText());
        if (m.find() && m.group().equals(txtEmailC.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Email");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Email");
            alert.showAndWait();

            return false;
        }
    }

    public boolean validateCINC() {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(txtCINC.getText());
        if (m.find() && m.group().equals(txtCINC.getText()) && txtCINC.getText().length() == 8) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate CIN");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid CIN");
            alert.showAndWait();

            return false;
        }

    }

    public boolean validatePasswordC() {
        // Pattern p = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}");
        // Matcher m = p.matcher(pwdC.getText());
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
        if (pwdC.getText().matches(pattern) && pwdCC.getText().equals(pwdCC.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Password must contain at least one(Digit, Lowercase, UpperCase and Special Character) and length must be between 6 -15");
            alert.showAndWait();

            return false;
        }

    }

    public boolean validateDateC() {
        if (dateC.getValue() == null) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;
        }
        Date date = java.sql.Date.valueOf(dateC.getValue());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-dd-MM");
        Calendar c = Calendar.getInstance();
     
        if (c.getTime().after(date) == true && c.getTime().equals(date) == false) {
            return true;

        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Validate Date");
            alert.setHeaderText(null);
            alert.setContentText("Enter valid date");
            alert.showAndWait();
            return false;

        }

    }

    @FXML
    public void InscriptionC(ActionEvent event) throws SQLException {
        String email = txtEmailC.getText();
        String username = txtUsernameC.getText();
        String password = pwdC.getText();
        String passwordC = pwdCC.getText();
        String prenom = txtPrenomC.getText();
        String nom = txtNomC.getText();
        String cin = txtCINC.getText();
        String sexe = SexeC.getValue();
        LocalDate date_naissanceP = dateC.getValue();
        String adresse = txtAdresseC.getText();
        String num_tel = txtNumC.getText();
        String photo_profil;
        SendMail sm = new SendMail();
        float Nb_Points = 0;
        int status = 0;
        int role = 1;
        Client P = new Client(Nb_Points, email, username, password, prenom, nom, cin, sexe, date_naissanceP, adresse, num_tel, status, role);
        System.out.println(P);
        Service_Client ss = new Service_Client();
        if (validateDateC() == true & validateCINC() & validateNomC() & validateEmaillC() & validatePasswordC()) {

            if ((ss.insert(P) == 0)) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Validate CIN");
                alert.setHeaderText(null);
                alert.setContentText("CIN déja existant");
                alert.showAndWait();
                System.err.println("Wrong inscription --///");
            } else {
                sm.SendEmail(email);
                /*JOptionPane.showMessageDialog(null, "Inscription reussie");*/
                Alert A1 = new Alert(Alert.AlertType.INFORMATION);
                A1.setTitle("Confirmation d'inscription");
                A1.setHeaderText("Inscription Réussie");
                A1.setContentText("Un mail de vérification a été envoyé, veuillez vérifier votre compte avant de vous connecter.");
                A1.showAndWait();
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

            }
        }

    }

}
