/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

/**
 *
 * @author Nckey Larson
 */


import Entities.Facture;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import com.itextpdf.text.Image;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;



public class pdf {
    

    public void creation(int id )
    { 
  
   
   
   
        
        try {

    try (OutputStream file = new FileOutputStream(new File("C:\\wamp64\\www\\GestionE_Shop\\uploads\\testpdf.pdf"))) {
            
        Document document = new Document();
        PdfWriter.getInstance(document, file);
        document.open();
        document.add(new Paragraph(new Date().toGMTString()));
        Font bold = new Font(FontFamily.COURIER, 12, Font.NORMAL, BaseColor.GREEN);
        Font b = new Font(FontFamily.COURIER, 12, Font.NORMAL, BaseColor.DARK_GRAY);
        Paragraph paragraph = new Paragraph("SHOPETAL",bold);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        paragraph = new Paragraph("Facture ",bold);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);
        paragraph = new Paragraph("\n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        FactureService fs = new FactureService();
        Facture fa= fs.getFacturebyid(id);
            paragraph = new Paragraph(fa.toString(),b);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.setIndentationLeft(50);
            document.add(paragraph);
  
        paragraph = new Paragraph("\n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph("\n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph(" \n");
        document.add(paragraph);
        paragraph = new Paragraph("Facture",bold);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        paragraph = new Paragraph("sidek nizar",b);
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
        document.close();
             
            
        }

        } catch (Exception e) {
            System.out.println("bbbbbbbbbbbbbbbbbb");


            e.printStackTrace();

        }

    }

    }




   


    



