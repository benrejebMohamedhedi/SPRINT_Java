/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.AffichageAjout;
import entities.service;
import connexion.conDB;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;


/**
 * FXML Controller class
 *
 * @author karim
 */
public class GestionAjouter implements Initializable {
Connection cnxC;
     public GestionAjouter () throws SQLException {
       cnxC = conDB.getInstance().getCnx();
    }
       @FXML
    private Label label;
     @FXML private TextField txtCategorie;
   @FXML private TextField txtsous_categorie;
     @FXML private TableView<service> table ; 
     @FXML private TableColumn<service,String> id_service ;
     @FXML private TableColumn<service,String> categoriee ;
     @FXML private TableColumn<service,String> sous_categoriee ;
     @FXML private TableColumn<service,String> image ;
       @FXML private TextField txtid;
         @FXML private TextField txtcategorieservice;
           @FXML private TextField txtsous_categorieservice;
           
           @FXML
  private FontAwesomeIconView image1;
    @FXML
    private ImageView pic1;
     /*
      @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
      
         Image image1 = pic1.getImage();
            String nameImage1 = saveToFileImageNormal(image1);
        String categorie= txtCategorie.getText();
          String sous_categorie= txtsous_categorie.getText();
          service s=new service(categorie,sous_categorie);
            s.setImage(nameImage1);
          AffichageAjout sg =new AffichageAjout();
          sg.ajouterService(s);
          id_service.setCellValueFactory(new PropertyValueFactory<>("id_service"));
       categoriee.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        sous_categoriee.setCellValueFactory(new PropertyValueFactory<>("sous_categorie"));
        
         
       
    }
*/
    @FXML public void getAll(ActionEvent event) throws SQLException{
       AffichageAjout sg =new AffichageAjout();
       id_service.setCellValueFactory(new PropertyValueFactory<>("id_service"));
        categoriee.setCellValueFactory(new PropertyValueFactory<>("categorie"));
       sous_categoriee.setCellValueFactory(new PropertyValueFactory<>("sous_categorie"));
       table.setItems(sg.afficherService());
       
    }
    /*
     public void getService(ActionEvent event) throws SQLException,ParseException {

          String idS =txtid.getText();
 int id_service=Integer.parseInt(idS);
 service s=AffichageAjout.getServiceId(id_service);
txtcategorieservice.setText(s.getCategorie());
txtsous_categorieservice.setText(s.getSous_categorie());

 
     }
*/
    /*
     public void updateService(ActionEvent event)throws SQLException{
       
         String idS = txtid.getText();
         int id_service =Integer.parseInt(idS);
         String cata=txtcategorieservice.getText();
         String sous_cata =txtsous_categorieservice.getText();
        service s =new service();
        
         
        
         s.setId_service(id_service);
         s.setCategorie(cata);
         s.setSous_categorie(sous_cata);
        int status=AffichageAjout.update(s);
        if(status>0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("service");
            alert.setHeaderText("INFORMATIONS");
            alert.setContentText("service bien ajouter !!");
            alert.showAndWait();
            //getAll(event);
        }
        else{
            
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle(" Ajout service");
            alert.setHeaderText("INFORMATIONS");
            alert.setContentText("service Non  Ajouter !!");
            alert.showAndWait();
        }
        
        
     
         
        
        
        
    }
    */
    public void deleteService(ActionEvent event)throws SQLException{
        
         String query = "DELETE FROM SERVICE where id_service ='"+txtid.getText()+"' " ;
        PreparedStatement pstm     =   cnxC.prepareStatement(query);
        pstm.executeUpdate();
        
        
        
    }
    public static String saveToFileImageNormal(Image image)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:/wamp/www/gestionservice/image");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bImage, "png", outputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return name;
    }

    @FXML
    private void addImage(MouseEvent event) throws IOException{
        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            pic1.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
}
