/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Demande;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author Lenovo
 */
public class ListOffres extends ListCell<Demande> {
        private final GridPane gridPane = new GridPane();  
    private final Label description = new Label();
    private final Label prix = new Label();
    private final Label  date_rdv = new Label();  
   private final Label descriptionLabel = new Label();
  private final Label textdate = new Label();
    private final Label textdesc=new Label();
    private final Label textprix = new Label();
    public GridPane getGridPane() {
        return gridPane;
    }

    public Label getDescription() {
        return description;
    }

    public Label getPrix() {
        return prix;
    }

    public Label getDate_rdv() {
        return date_rdv;
    }

    public Label getDescriptionLabel() {
        return descriptionLabel;
    }

    public AnchorPane getContent() {
        return content;
    }
   
   
    private final AnchorPane content = new AnchorPane();
    public ListOffres(){
             GridPane.setConstraints(description, 1, 1); 
        GridPane.setConstraints(textdate, 0, 0); 
        GridPane.setConstraints(textdesc, 0, 1); 
        GridPane.setConstraints(textprix, 0, 2); 
          textdate.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;-fx-alignement: center; -fx-border-style: none; "); 
        textdate.setText("la date : ");
          textdesc.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;-fx-alignement: center; -fx-border-style: none; "); 
        textdesc.setText("description de la demande : ");
        gridPane.setStyle("-fx-border-style: none;");
        textprix.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;-fx-alignement: center; -fx-border-style: none; "); 
        textprix.setText("le prix de la demande : ");
       /* prix.setStyle("-fx-font-style: bold; -fx-font-size: 1.2em;-fx-border-style: none;");*/ 
        GridPane.setConstraints(prix, 1,2); 
        //gridPane.setStyle("-fx-background-color: #ffff; ");
        gridPane.setStyle("-fx-border-style: none;");
        date_rdv.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;-fx-alignement: center; -fx-border-style: none; "); 
        GridPane.setConstraints(date_rdv, 1, 0); 
        
        //GridPane.setColumnSpan(Description, Integer.MAX_VALUE); 
        
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.CENTER, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
      
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
      
        gridPane.setHgap(3); 
        gridPane.setVgap(3); 
       
        gridPane.getChildren().setAll(textdesc,textdate,textprix,description, prix, date_rdv); 
        
        
        AnchorPane.setTopAnchor(gridPane, 0d); 
        AnchorPane.setLeftAnchor(gridPane, 0d); 
        AnchorPane.setBottomAnchor(gridPane, 0d); 
        AnchorPane.setRightAnchor(gridPane, 0d); 
       
        content.getChildren().add(gridPane); 
    }
     protected void updateItem(Demande item, boolean empty) { 
        super.updateItem(item, empty); 
        setGraphic(null); 
        setText(null); 
        setContentDisplay(ContentDisplay.LEFT); 
        if (!empty && item != null) { 
           
            description.setText(item.getDescription()); 
            prix.setText(String.valueOf(item.getPrix()));
            date_rdv.setText(String.valueOf(item.getDate_rdv()));
            //brandIcon.setImage(item.getBrandImage()); 
            //carIcon.setImage(item.getCarImage()); 
            descriptionLabel.setText(String.format("%s ,%s, %s", item.getDescription(), item.getPrix(),item.getDate_rdv())); 
           //colorRect.setFill(item.getColor()); 
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY); 
        } 
        
}
}
