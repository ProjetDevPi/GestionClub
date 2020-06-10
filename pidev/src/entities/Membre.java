/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author User
 */
public class Membre {
    private int id;
    private int idclub;
    private int ideleve;
    private int id_user;
private String parent;
private String club;
private String eleve;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getEleve() {
        return eleve;
    }

    public void setEleve(String eleve) {
        this.eleve = eleve;
    }

    public Membre(int id, int idclub, int ideleve, int id_user) {
        this.id = id;
        this.idclub = idclub;
        this.ideleve = ideleve;
        this.id_user = id_user;
    }

     public Membre() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdclub() {
        return idclub;
    }

    public void setIdclub(int idclub) {
        this.idclub = idclub;
    }

    public int getIdeleve() {
        return ideleve;
    }

    public void setIdeleve(int ideleve) {
        this.ideleve = ideleve;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
