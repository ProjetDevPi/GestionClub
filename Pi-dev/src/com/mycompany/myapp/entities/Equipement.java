/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author User
 */
public class Equipement {
    private int idclub;
    private int ideq;
    private String nom;
    private String type;
    private String nom_image;
private ImageView photo; 

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public Equipement(int ideq, String nom, String type, String nom_image) {
        this.ideq = ideq;
        this.nom = nom;
        this.type = type;
        this.nom_image = nom_image;
    }
    
    public Equipement(int idclub, int ideq, String nom, String type, String nom_image) {
        this.idclub = idclub;
        this.ideq = ideq;
        this.nom = nom;
        this.type = type;
        this.nom_image = nom_image;
    }
    public Equipement(int idclub, int ideq, String nom, String type, String nom_image, ImageView image) {
        this.idclub = idclub;
        this.ideq = ideq;
        this.nom = nom;
        this.type = type;
        this.nom_image = nom_image;
        this.photo=image;
    }
    public Equipement() {}


    public int getIdclub() {
        return idclub;
    }

    public void setIdclub(int idclub) {
        this.idclub = idclub;
    }

    public int getIdeq() {
        return ideq;
    }

    public void setIdeq(int ideq) {
        this.ideq = ideq;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }
    
    @Override
    public String toString() {
        return "Equipement{" + "idclub=" + idclub + ", ideq=" + ideq + ", nom=" + nom + ", type=" + type + ", nom_image=" + nom_image + '}';
    }

    

}
