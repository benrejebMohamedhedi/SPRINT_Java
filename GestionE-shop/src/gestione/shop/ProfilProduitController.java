/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestione.shop;

import DB.MyDBcon;
import Entities.Ligne_Commande;
import Entities.Produit;
import Services.Produit.ProduitService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import static org.apache.xalan.lib.ExsltDatetime.date;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import serviceLigneCom.Ligne_ComService;

/**
 * FXML Controller class
 *
 * @author nizar
 */
public class ProfilProduitController implements Initializable {

    @FXML
    private ImageView image1;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label description;
    @FXML
    private Label dejaevaluated;
    @FXML
    private JFXButton AddReview;
    @FXML
    private Label idEtab;
    @FXML
    private StackPane stackpane;
    @FXML
    private StackPane stackpane1;
    @FXML
    private StackPane stackpane2;
    @FXML
    private Label desc;
    @FXML
    private Label prix;
    @FXML
    private Tab tabX;
 @FXML
    private AnchorPane scroll;
    @FXML
    private FontAwesomeIconView goBack;
    @FXML
    private JFXTabPane tab;
  
    Ligne_ComService lc;
    int id_product ;
    java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
    /**
     * Initializes the controller class.
     */
    public Connection cnx; 
    @FXML
    private Button addpan;

    public ProfilProduitController() throws SQLException {
        this.lc = new Ligne_ComService();
    }
       public boolean existePanier(int id) throws SQLException {
        int count = 0;
        String req = "select count(*) from `commande` where id_user=? and etat=?";
        PreparedStatement pstm = cnx.prepareStatement(req);
        pstm.setInt(1, id);
        pstm.setInt(2, 0);
        ResultSet res = pstm.executeQuery();
        while (res.next()) {
            count = res.getInt(1);
        }
           System.out.println(count);
        if (count == 0) {
            
            return false;
        }
        return true;
    }
        public boolean existeProd(int id) throws SQLException {
        int count = 0;
        String req = "select count(*) from `ligne_commande` where id_Produit=?";
        PreparedStatement pstm = cnx.prepareStatement(req);
        pstm.setInt(1, id);
        
        ResultSet res = pstm.executeQuery();
        while (res.next()) {
            count = res.getInt(1);
        }
           System.out.println(count);
        if (count == 0) {
            
            return false;
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
    } 
     
   
 public void ProfilProduit(int id) throws SQLException {
id_product =id ;
      
       

        tab.getSelectionModel().select(0);
      ProduitService ps=new ProduitService();
            
            ArrayList<Produit> list = ps.getAllProduit();
            ObservableList<Produit> s = FXCollections.observableArrayList(list);
        Produit etab = s.filtered(e -> e.getId_produit()== id).get(0);
       

        idEtab.setText(String.valueOf(etab.getId_produit()));
        description.setText(etab.getDescription());
        name.setText(etab.getNom());
        prix.setText(etab.getPrix()+"");
       
      

        final String imageURI = new File("C://wamp64/www/GestionE_Shop/uploads/" + etab.getImage()).toURI().toString();
        image1.setImage(new Image(imageURI));

        
        
            JFXDialogLayout dialogLayout3= new JFXDialogLayout();
        dialogLayout3.setPrefHeight(700);
        dialogLayout3.setPrefWidth(1000);
       
     
        
          
       
        JFXDialog dialog3 = new JFXDialog(stackpane1, dialogLayout3, JFXDialog.DialogTransition.CENTER);
        dialog3.setStyle("-fx-background-color:transparent");
        dialog3.setAlignment(Pos.TOP_CENTER);

        stackpane1.setVisible(true);
        stackpane1.toFront();
      
        dialog3.show();
       
        
          

      
        

      
        

        ///////map////////
      
       
        ////// map wfé//////

        AddReview.setOnMouseClicked((MouseEvent event) -> {

           

        });

    }
     
      public void Reviewslist(Produit e) throws SQLException {

//        tab.getSelectionModel().select(tabX);
//        GestionReviews gr = new GestionReviews();
//        listreview = gr.ListReviews(e);
//
//        TilePane b = new TilePane();
//        b.setPadding(new javafx.geometry.Insets(30));
//        TilePane c = new TilePane();
//        for (Review rev : listreview) {
//            TilePane a = new TilePane();
//            Label title = new Label(rev.getTitre());
//            Label com = new Label(rev.getCommentaire());
//            title.setStyle("-fx-text-fill: #ff214f;-fx-font-size:20px;-fx-font-weight: bold");
//            FontAwesomeIconView star = new FontAwesomeIconView(FontAwesomeIcon.STAR);
//            FontAwesomeIconView trash = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//            FontAwesomeIconView edit = new FontAwesomeIconView(FontAwesomeIcon.EDIT);
//            edit.setStyle("-glyph-size:20px;-fx-fill:RED");
//            trash.setStyle("-glyph-size:20px;-fx-fill:GREEN");
//
//            FontAwesomeIconView star2 = new FontAwesomeIconView(FontAwesomeIcon.STAR);
//            star.setStyle("-glyph-size:20;-fx-fill:#FFD700");
//            star2.setStyle("-glyph-size:20px;-fx-fill:#FFD700");
//            Label servic = new Label("Service : " + String.valueOf(rev.getService()));
//            Label qualit = new Label("Qualite : " + String.valueOf(rev.getQualite()));
//            Label dat = new Label();
//            dat.setStyle("-fx-font-weight: bold");
//            qualit.setStyle("-fx-font-size:15px;;-fx-font-weight: bold");
//            servic.setStyle("-fx-font-size:15px;;-fx-font-weight: bold");
//
//            a.setStyle("-fx-background-color: #D8D8D8;-fx-max-height:250px;-fx-min-height:200px;-fx-max-width:25px;-fx-min-witdth:20px;");
//            Label espace = new Label();
//            com.setPrefWidth(550);
//            com.setWrapText(true);
//            a.getChildren().add(new VBox(5, title, dat, com, new HBox(4, qualit, star, servic, star2), new HBox(400, espace, new HBox(20, trash, edit))));
//            a.setPadding(new javafx.geometry.Insets(10, 5, 10, 5));
//            a.setMaxSize(600, 150);
//            a.setPrefColumns(2);
//            c.getChildren().add(a);
//            trash.setOnMouseClicked((MouseEvent event) -> {
//                GestionReviews grev = new GestionReviews();
//                try {
//
//                    int newtotal = gr.NbrReview(e);
//                    e.setTotalqualite(e.getTotalqualite() - rev.getQualite());
//                    e.setTotalservice(e.getTotalservice() - rev.getService());
//                    e.setMoyqualite(e.getTotalqualite() / (newtotal - 1));
//                    e.setMoyservice(e.getTotalservice() / (newtotal - 1));
//                    EditEtablissement EE = new EditEtablissement();
//                    EE.ModifierReview(e, e.getId());
//                    grev.DeleteReveiw(rev.getId());
//                    System.out.println("deleted");
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ProfilResto.fxml"));
//                    Parent root = loader.load();
//                    ProfilrestoController PR = loader.getController();
//
//                    PR.RestoProfil(e.getId());
//                    PR.Reviewslist(e);
//                    name.getScene().setRoot(root);
//                } catch (SQLException ex) {
//                    Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(ProfilrestoController.class.getName()).log(Level.SEVERE, null, ex);
//                }
//
//            });
//        }
//        c.setPrefColumns(2);
//        c.setPadding(new javafx.geometry.Insets(50, 0, 0, 0));
//        c.setHgap(40);
//        c.setVgap(40);
//        b.getChildren().add(c);
//        fp.getChildren().add(b);
//        scroll.getChildren().add(fp);
        TilePane b = new TilePane();
        tab.getSelectionModel().select(tabX);
        b.setPadding(new javafx.geometry.Insets(30));
        TilePane c = new TilePane();

    
        c.setPrefColumns(2);
        c.setPadding(new javafx.geometry.Insets(0));
        c.setHgap(25);
        c.setVgap(50);
        b.getChildren().add(c);
        b.setPrefWidth(1000);
        scroll.getChildren().add(b);
        
      }
    @FXML
    private void back(MouseEvent event) throws IOException, SQLException {
      int oj=0;
        
         System.out.println("heloooooo");
         cnx = MyDBcon.getInstance().getCnx();
           
                try {
                    int  iduser=1;
                    ProduitService ps=new ProduitService();
                    ArrayList<Produit> list = ps.getAllProduit();
                    ObservableList<Produit> s = FXCollections.observableArrayList(list);
                    Produit etab = s.filtered(j -> j.getId_produit()== id_product).get(0);
                    int id_panier=0;
                    String r = "SELECT `id_commande` from `commande` where `id_user`=?";
                    PreparedStatement pst = ps.cnx.prepareStatement(r);
                    pst.setInt(1, iduser);
                    ResultSet res = pst.executeQuery();
                    
                    while (res.next()) {
                        
                        id_panier=res.getInt("id_commande");
                        
                    }
                    if (existePanier(iduser)) {
                        String req = "select id_produit,quantite from `ligne_commande` where id_commande=?";
                        PreparedStatement  psss = cnx.prepareStatement(req);
                        psss.setInt(1,id_panier );
                        ResultSet resultat = psss.executeQuery();
                        
                        while (resultat.next()) {
                            
                            System.out.println(resultat.getInt("id_produit"));
                            if (existeProd(id_product)) {
                                System.out.println("Produit déjà ajouté");
                                lc.modifierQuantite(resultat.getInt("quantite")+1, etab);
                            } else {
                               lc.ajouterLigne(id_product,id_panier);
                                }
                                
                            }
                      
                       
                    } else {
                        String req = "INSERT INTO `commande`(`date`,`id_user`) VALUES (?,?)";
                        PreparedStatement pstm = cnx.prepareStatement(req);
                        pstm.setDate(1,date);
                        // pstm.setBoolean(2, p.isEtat());
                        pstm.setInt(2, iduser);
                        pstm.executeUpdate();
                        System.out.println("Insertion panier done");
                        String req2 = "select id_commande from `commande` where id_user=?";
                        pstm = cnx.prepareStatement(req2);
                        pstm.setInt(1, iduser);
                        ResultSet res2 = pstm.executeQuery();
                        
                        while (res2.next()) {
                            
                            id_panier=res2.getInt("id_commande");
                            
                        }
                        lc.ajouterLigne(id_product,id_panier);
                        
                    }
                    System.out.println("done");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ProfilProduitController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        
        /* Parent root= FXMLLoader.load(getClass().getResource("ListProduit.fxml"));
                      
                        goBack.getScene().setRoot(root);
                              */
        
    }

    @FXML
    private void addp(ActionEvent event)  {
        System.out.println("heloooooo");
         
          /*  
                
        
        */
    }

    }

    

