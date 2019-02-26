/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;
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
import Entities.Produit;
import Service.ProduitService;
import Service.ProduitService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

/**
 *
 * @author nizar
 */
public class FXMLDocumentController implements Initializable {
    @FXML private ComboBox<String> cateorie;
    @FXML private TextField nom;
        @FXML private TextField reference;
   //@FXML private ComboBox ;
   @FXML private TextField quantite;
    @FXML private TextField prix;
    //@FXML private TextField image;
    @FXML private TextArea descr;
@FXML private Button ajouter;
@FXML
  private FontAwesomeIconView image1;
    @FXML
    private ImageView pic1;

            @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
     
            cateorie.getItems().addAll("bricollage","assemblage","technolage");
            cateorie.getSelectionModel().select("bricollage");
            
            
            
            ajouter.setOnAction(e ->
                    
                    
                 
            {  
                try {  
                    Image image1 = pic1.getImage();
                    String nameImage1 = saveToFileImageNormal(image1);
                    
                    
                    try {
                        String nomp= nom.getText();
                      //  String imaa = image.getText();
                        String ref = reference.getText();
                        String categ= cateorie.getValue();
                        String quait= quantite.getText();
                        String pri= prix.getText();
                        String desc=descr.getText();
                        // Image imag= new Image(""+imaa);
                        // img.;
                        int prixx= Integer.parseInt(pri);
                        int qt=Integer.parseInt(quait);
                        Produit p =new Produit();
                        p.setNom(nomp);
                        p.setQuantite(qt);
                        p.setCateorie(categ);
                        p.setPrix(prixx);
                        p.setImage(nameImage1);
                        p.setQuantite(qt);
                        p.setReference(ref);
                        p.setDescription(desc);
                        //p.setImage(imaa);
                        ProduitService ps=new ProduitService();
                        ps.ajouterAlaBaseProduit(p);
                        Parent root= FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
                        ajouter.getScene().setRoot(root);
                        
                        
                        
                        
                        
                        System.out.println("done");
                    } catch (SQLException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                } catch (SQLException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                       
            
            
            
            
            
            
            
            
            
            
        } );

            
            
            
            
    }
    public static String saveToFileImageNormal(Image image)throws SQLException  {

       
         String ext = "jpg";
        File dir = new File("C:/wamp64/www/GestionE_Shop/uploads");
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
}

