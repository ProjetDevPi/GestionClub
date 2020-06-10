/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Yassiine
 */
public class login {
    private int id;
    private int id_user;
    private String username;
     private String mail;

    public login() {
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public login(int id_user, String username, String mail) {
        this.id_user = id_user;
        this.username = username;
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "login{" + "id=" + id + ", id_user=" + id_user + ", username=" + username + ", mail=" + mail + '}';
    }
     
     
}
