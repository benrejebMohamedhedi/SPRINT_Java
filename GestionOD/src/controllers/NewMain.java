/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Entities.Demande;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 *
 * @author Lenovo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws SQLException, ParseException {
        Demande d=new Demande( 2, 3, "Des", 11, "sd", new Date(2019,03,03),1);
         // Demande d2=new Demande( 2, 3, "Help222", 11, "sd", new Date(1994, 03, 03));
        
        // TODO code application logic here
        DemandeServices Ds=new DemandeServices();
        
                Ds.ajouterDemande(d);
    //   Ds.deleteDemande(d,2);
      //  Ds.UpdateDemande(5,d2);
     //   Ds.UpdateDemande(1, d2);
    
    
/*	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			java.util.Date datetest = sdf.parse("2019-02-11");
		
		
		for(Demande cmd : Ds.findByDate(datetest))
		{
	System.out.println(cmd);
		}
                } catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
    
    */
}
}