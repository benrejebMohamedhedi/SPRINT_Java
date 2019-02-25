/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import Entities.Produit;
import Service.ProduitService;
import Service.ProduitService;
import Service.ProduitService;
import Service.ProduitService;
import Service.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class AffichageProduitController implements Initializable {

    @FXML
    private TableView<Produit> tableproduit;
    @FXML
    private TableColumn<Produit, String> nom;
    @FXML
    private TableColumn<Produit, String> ref;
    @FXML
    private TableColumn<Produit, Integer> prx;
    @FXML
    private TableColumn<Produit,Integer> qt;
    @FXML
    private TextField filterInputs;
    //************************************//
    
    
    @FXML
    private Button deleteBtn;
    @FXML
    private Button ajoutergo;
    @FXML
    private Button modifiergo;
    @FXML
    private Button listt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
             ProduitService ps=new ProduitService();
            
            ArrayList<Produit> list = ps.getAllProduit();
            ObservableList<Produit> obb = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<> ("nom"));
            ref.setCellValueFactory(new PropertyValueFactory<> ("reference"));
            prx.setCellValueFactory(new PropertyValueFactory<> ("prix"));
            qt.setCellValueFactory(new PropertyValueFactory<> ("quantite"));
            tableproduit.setItems(obb);
            //add Listener to filterField
            filterInputs.textProperty().addListener(new ChangeListener() {
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    try {
                        filterProduitList((String) oldValue, (String) newValue);
                    } catch (SQLException ex) {
                        Logger.getLogger(AffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
                
            });
            if (filterInputs.getText()== null)
            {
                ProduitService pss=new ProduitService();
              ArrayList<Produit> lisst = pss.getAllProduit();
            ObservableList<Produit> obbb = FXCollections.observableArrayList(list);
            nom.setCellValueFactory(new PropertyValueFactory<> ("nom"));
            ref.setCellValueFactory(new PropertyValueFactory<> ("reference"));
            prx.setCellValueFactory(new PropertyValueFactory<> ("prix"));
            qt.setCellValueFactory(new PropertyValueFactory<> ("quantite"));
            tableproduit.setItems(obb);  
            }
            //initialize editable attributes
            tableproduit.setEditable(true);
            nom.setOnEditCommit(e -> NameCol_OnEditCommit(e));
            ref.setOnEditCommit(e -> RefCol_OnEditCommit(e));
            prx.setOnEditCommit(e -> PrixCol_OnEditCommit(e));
            qt.setOnEditCommit(e -> QuantIDCol_OnEditCommit(e));
            
            
            tableproduit.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            
            nom.setCellFactory(TextFieldTableCell.forTableColumn());
            ref.setCellFactory(TextFieldTableCell.forTableColumn());
           // prx.setCellFactory(TextFieldTableCell.forTableColumn());
            //qt.setCellFactory(TextFieldTableCell.forTableColumn());
            
           
            
            //initialize gender ComboBox
            
            tableproduit.setEditable(true);
            tableproduit.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            tableproduit.setPlaceholder(new Label("Your Table is Empty"));
        } //end initialize
        catch (SQLException ex) {
            Logger.getLogger(AffichageProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }

    /*
    ----------------------------------------------Control handlers---------------------------------------------
     */
 
    public void NameCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Produit, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Produit, String>) e;
        Produit p = cellEditEvent.getRowValue();
        p.setNom(cellEditEvent.getNewValue());
    }
    public void RefCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Produit, String> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Produit, String>) e;
        Produit p = cellEditEvent.getRowValue();
        p.setReference(cellEditEvent.getNewValue());
    }
    public void PrixCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Produit, Integer> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Produit, Integer>) e;
        Produit p = cellEditEvent.getRowValue();
        p.setPrix(cellEditEvent.getNewValue());
    }
    public void QuantIDCol_OnEditCommit(Event e) {
        TableColumn.CellEditEvent<Produit, Integer> cellEditEvent;
        cellEditEvent = (TableColumn.CellEditEvent<Produit, Integer>) e;
        Produit p = cellEditEvent.getRowValue();
        p.setQuantite(cellEditEvent.getNewValue());
    }
   
  
   
    @FXML
    public void handleDeleteButtonClick(ActionEvent event) throws SQLException {
        ProduitService ps = new ProduitService();
       ArrayList<Produit> list = ps.getAllProduit();
            ObservableList<Produit> observableProduitList = FXCollections.observableArrayList(list);
        if(!observableProduitList.isEmpty()) {
            System.out.println("Delete button clicked");
            Alert deleteAlert = new Alert(Alert.AlertType.WARNING, "Confirm", ButtonType.OK, ButtonType.CANCEL);
            Window owner = ((Node) event.getTarget()).getScene().getWindow();
            deleteAlert.setContentText("Are you sure you want to delete this?\n\nTHIS CANNOT BE UNDONE.");
            deleteAlert.initModality(Modality.APPLICATION_MODAL);
            deleteAlert.initOwner(owner);
            deleteAlert.showAndWait();
            if(deleteAlert.getResult() == ButtonType.OK) {
                ps.supprimerPrd(tableproduit.getSelectionModel().getSelectedItem().getReference());
                observableProduitList.removeAll(tableproduit.getSelectionModel().getSelectedItems());
                tableproduit.getSelectionModel().clearSelection();
                tableproduit.setItems(observableProduitList);
                
               
                
            }
            else {
                deleteAlert.close();
            }
        }
    }
   
    //filter table by first or last name
    public void filterProduitList(String oldValue, String newValue) throws SQLException {
         ProduitService ps = new ProduitService();
       ArrayList<Produit> list = ps.getAllProduit();
            ObservableList<Produit> observableProduitList = FXCollections.observableArrayList(list);
        ObservableList<Produit> filteredList = FXCollections.observableArrayList();
        if(filterInputs == null || (newValue.length() < oldValue.length()) || newValue == null) {
            tableproduit.setItems(observableProduitList);
        }
        else {
            newValue = newValue.toUpperCase();
            for(Produit p : tableproduit.getItems()) {
                String Name = p.getNom();
                String ref = p.getReference();
                if(Name.toUpperCase().contains(newValue) || ref.toUpperCase().contains(newValue)) {
                    filteredList.add(p);
                }
            }
            tableproduit.setItems(filteredList);
        }
    }
    @FXML 
    public void goToAjouter(ActionEvent event) throws IOException
    {
         Parent root= FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
               ajoutergo.getScene().setRoot(root);
        
    }
    @FXML void goToModifier (ActionEvent event) throws IOException
    {
        Parent root= FXMLLoader.load(getClass().getResource("ModifierP.fxml"));
               modifiergo.getScene().setRoot(root);
    }
    @FXML void gotolist (ActionEvent event) throws IOException
    {
        
        Parent root= FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
               listt.getScene().setRoot(root);
    }
  
    }    
    

