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
public class Club {
    private int idclub;
    private String nomclub;
    private int cap;
    private String nom_image;
private ImageView photo; 

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public Club(String nomclub, int cap, String nom_image) {
        this.nomclub = nomclub;
        this.cap = cap;
        this.nom_image = nom_image;
    }

    public Club(String nomclub, int cap, String nom_image, ImageView photo) {
        //this.idclub = idclub;
        this.nomclub = nomclub;
        this.cap = cap;
        this.nom_image = nom_image;
        this.photo = photo;
    }
     public Club(int idclub, String nomclub, int cap, String nom_image) {
        this.idclub = idclub;
        this.nomclub = nomclub;
        this.cap = cap;
        this.nom_image = nom_image;
    }
    public Club() {}

    public int getIdclub() {
        return idclub;
    }

    public void setIdclub(int idclub) {
        this.idclub = idclub;
    }

    public String getNomclub() {
        return nomclub;
    }

    public void setNomclub(String nomclub) {
        this.nomclub = nomclub;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    @Override
    public String toString() {
        return "club{" + "idclub=" + idclub + ", nomclub=" + nomclub + ", cap=" + cap + ", nom_image=" + nom_image + '}';
    }
    
}
