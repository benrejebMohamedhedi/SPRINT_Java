/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Demande;
import Entities.Prestataire;
import com.jfoenix.controls.JFXTextField;
import connexion.conDB;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class AjouterDemandePersonnaliseController implements Initializable {
    @FXML
    private FontAwesomeIconView image1;
    @FXML
    private ImageView pic1;
    @FXML
    private TextArea txt_description;
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField txt_Prix;
  

         @FXML private TableView<Prestataire> table_prestataire;
         @FXML
    private TableColumn<Prestataire, Integer> col_id;
    @FXML
    private TableColumn<Prestataire, String> col_nom;
    @FXML
    private TableColumn<Prestataire, String> col_prenom;
    @FXML
    private TextField txt_idP;
    @FXML
    private Button AjouterDemande;
    @FXML
    private JFXTextField txt_nomP;
     
    /**
     * Initializes the controller class.
     * @throws java.sql.SQLException
     */
    
     public AjouterDemandePersonnaliseController() throws SQLException{
        Connection cnx = conDB.getInstance().getCnx();
    }
     
      public void getAll(ActionEvent event) throws SQLException{
    
            DemandeServices sg =new DemandeServices();
            col_id.setCellValueFactory(new PropertyValueFactory<>("id_prestataire"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            table_prestataire.setItems(sg.fillprestatireLIstt());

   }
      @FXML
    private void AjouterDemande(ActionEvent event) throws SQLException, IOException {
           SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd"); 
            Image image1 = pic1.getImage();
            String nameImage1 = saveToFileImageNormal(image1);
            String Description= txt_description.getText();
            int id_prestataire=Integer.parseInt(txt_idP.getText());
            
        int prix=Integer.parseInt(txt_Prix.getText());
          java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date.getValue());
          
          int statut=1;
        
      //    Demande s=new Demande( Description, Prix , gettedDatePickerDate); // mochkla 
  
        Demande s =new Demande(Description,prix,nameImage1, gettedDatePickerDate);
         //      s.setImage(nameImage1);
       // s.setPrix(prix);
       s.setId_prestataire(id_prestataire);
          DemandeServices Ds =new DemandeServices();
          Ds.ajouterDemande(s);
          new  Alert(Alert.AlertType.CONFIRMATION,"Demande Inséré",ButtonType.OK).show();
       //   FXMLLoader.load(getClass().getResource("/vues/ListeDemandes.fxml"));
    }
    public static String saveToFileImageNormal(Image image1)throws SQLException  {

        String ext = "jpg";
        File dir = new File("C:/Users/Lenovo/Documents/NetBeansProjects/GestionOD/src/uploads/images");
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        File outputFile = new File(dir, name);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image1, null);
        
        return name;
    }
    @FXML
      public void getSelected(MouseEvent event) throws SQLException{
          
            Prestataire P = table_prestataire.getSelectionModel().getSelectedItem();
            if(P != null){
                
                
               txt_idP.setText(P.getId_prestataire()+"");
               txt_nomP.setText(P.getNom());
            }
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
    public void initialize(URL url, ResourceBundle rb){
        // TODO
         DemandeServices sg;
        try {
            sg = new DemandeServices();
              col_id.setCellValueFactory(new PropertyValueFactory<>("id_prestataire"));
            col_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            col_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            table_prestataire.setItems(sg.fillprestatireLIstt());
        } catch (SQLException ex) {
            Logger.getLogger(AjouterDemandePersonnaliseController.class.getName()).log(Level.SEVERE, null, ex);
        }
          

    }    

   
    
}
