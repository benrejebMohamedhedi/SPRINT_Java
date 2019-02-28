/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author karim
 */
public class service {
 
    
    private int id_service ; 
    private String categorie ; 
    private String sous_categorie ; 
    private String image ; 

    public service(String categorie, String sous_categorie) {
        this.categorie = categorie;
        this.sous_categorie = sous_categorie;
    }

    public service(int id_service, String categorie, String sous_categorie) {
        this.id_service = id_service;
        this.categorie = categorie;
        this.sous_categorie = sous_categorie;
    }

    public service(String categorie, String sous_categorie, String image) {
        this.categorie = categorie;
        this.sous_categorie = sous_categorie;
        this.image = image;
    }

    public service(int id_service, String categorie, String sous_categorie, String image) {
        this.id_service = id_service;
        this.categorie = categorie;
        this.sous_categorie = sous_categorie;
        this.image = image;
    }

   
    
    public service()
    {
        
    }
    public int getId_service() {
        return id_service;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getSous_categorie() {
        return sous_categorie;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setSous_categorie(String sous_categorie) {
        this.sous_categorie = sous_categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "service{" + "id_service=" + id_service + ", categorie=" + categorie + ", sous_categorie=" + sous_categorie + ", image=" + image + '}';
    }

   
    
    
    
    
    
    
    
    
    
}
