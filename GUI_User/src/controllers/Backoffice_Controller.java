/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entity.Client;
import Entity.Prestataire;
import Entity.UserSession;
import Services.Service_Client;
import Services.Service_Prestataire;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author NacimGastli
 */
public class Backoffice_Controller implements Initializable {

   
    @FXML
    private TableView<Prestataire> tablePrestataire;
    @FXML
    private ScrollPane sp;
    @FXML
    private TableColumn<Client, String> col_id;
    @FXML
    private TableColumn<Client, String> col_username;
    @FXML
    private TableColumn<Client, String> col_nom;
    @FXML
    private TableColumn<Client, String> col_prenom;
    @FXML
    private TableColumn<Client, String> col_cin;
    @FXML
    private TableColumn<Client, String> col_mail;
    @FXML
    private TableColumn<Client, String> col_point;
    @FXML
    private TableColumn<Client, String> col_status;
    @FXML
    private TableColumn<Client, String> col_desactiver;
    Service_Client ss = new Service_Client();
    Service_Prestataire k = new Service_Prestataire();
    
    private ObservableList<Client> obslist;
      @FXML
    private TableView<Client> tableClient;
    @FXML
    TableColumn<Prestataire, String> id_p;
    @FXML
    TableColumn<Prestataire, String>  nom_p;
    @FXML
    TableColumn<Prestataire, String> prenom_p;
    @FXML
    TableColumn<Prestataire, String> cin_p;
    @FXML
    TableColumn<Prestataire, String>  mail_p;
    @FXML
    TableColumn<Prestataire, String>  spec_p;
    @FXML
    TableColumn<Prestataire, String> etat_p;
    @FXML
    TableColumn<Prestataire, String>  status_p;
    @FXML
    TableColumn<Prestataire, String>  des_p;
    @FXML
    private TableColumn<Prestataire, String> username_p;
    @FXML
    private TableColumn<?, ?> col_num;
    @FXML
    private TableColumn<?, ?> num_p;
    @FXML
    private Button btnLogOut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setFitToHeight(true);
        sp.setHmax(3);
        sp.setHvalue(0);
        sp.setDisable(false);
        RefreshP();
        RefreshC();

              
 
//           
   }
    public void RefreshP(){
        tablePrestataire.setItems(null);
        id_p.setCellValueFactory(new PropertyValueFactory<>("id"));
        username_p.setCellValueFactory(new PropertyValueFactory<>("username"));
        nom_p.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_p.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cin_p.setCellValueFactory(new PropertyValueFactory<>("cin"));
        mail_p.setCellValueFactory(new PropertyValueFactory<>("email"));
        num_p.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        spec_p.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        etat_p.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        status_p.setCellValueFactory(new PropertyValueFactory<>("status"));
        des_p.setCellValueFactory(new PropertyValueFactory<>("desactiver"));
        tablePrestataire.setItems(k.getshow());
    }
    
    public void RefreshC(){
          tableClient.setItems(null);
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        col_cin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_num.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        col_point.setCellValueFactory(new PropertyValueFactory<>("Nb_Points"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_desactiver.setCellValueFactory(new PropertyValueFactory<>("desactiver"));
        tableClient.setItems(ss.getshow());
    }

    @FXML
    private void Deconnexion(ActionEvent event) throws IOException {
        Node node;
        node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/gui_user/LoginUser.fxml")));
        stage.setScene(scene);
        stage.show();
    }

    
}
