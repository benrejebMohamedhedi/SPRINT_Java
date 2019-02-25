/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Demande;
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
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;

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
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class ControllerDemande implements Initializable {
    
    
    Connection cnx;
    @FXML
    private ImageView pic1;
    @FXML
    private TextField txt_Prix;
    @FXML
    private TextArea txt_description;
    @FXML
    private DatePicker date;
   
    @FXML
  private Button AjouterDemande;
    @FXML
    private FontAwesomeIconView image1;
     public ControllerDemande() throws SQLException{
              cnx = conDB.getInstance().getCnx();
    }
     
      @FXML
    private void AjouterDemande(ActionEvent event) throws SQLException, IOException {
                SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd");
                Image image1 = pic1.getImage();
                String nameImage1 = saveToFileImageNormal(image1);
                String Description= txt_description.getText();


               int prix=Integer.parseInt(txt_Prix.getText());
                 java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date.getValue());

                 int statut=1;
        
      //    Demande s=new Demande( Description, Prix , gettedDatePickerDate); // mochkla 
  
        Demande s =new Demande(Description,prix,nameImage1, gettedDatePickerDate);
         //      s.setImage(nameImage1);
       // s.setPrix(prix);
          DemandeServices Ds =new DemandeServices();
          Ds.ajouterDemande(s);
          new  Alert(Alert.AlertType.CONFIRMATION,"Demande Inséré",ButtonType.OK).show();
         
           
    }
    public static String saveToFileImageNormal(Image image)throws SQLException  {

      String ext = "jpg";
        File dir = new File("C:/wamp64/www/image");
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

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (.png)", "*.PNG");
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
