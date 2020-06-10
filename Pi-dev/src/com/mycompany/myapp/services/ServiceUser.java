/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.FosUser;

import com.mycompany.myapp.entities.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bhk
 */
public class ServiceUser {


    public static ServiceUser instance=null;
      ArrayList<login> login = new ArrayList<>();
    ArrayList<FosUser> allusers = new ArrayList<>();
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }
    
public static login currentUser=new login();
     
       public ArrayList<FosUser> users() {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/dev/web/app_dev.php/login2");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                try {
                    allusers = ser.parseUsers(new String(con.getResponseData()));
                } catch (IOException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return allusers;
    }
       
        public ArrayList<FosUser> parseUsers(String json) throws IOException {

        ArrayList<FosUser> log = new ArrayList<>();

        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
        for (Map<String, Object> obj : list) {
            //Création des tâches et récupération de leurs données
            FosUser e = new FosUser();
            
            float id = Float.parseFloat(obj.get("id").toString());
            e.setId((int) id);
            
            e.setEmail(obj.get("email").toString());
            e.setUsername(obj.get("username").toString());
           e.setPassword(obj.get("password").toString());
           
            
            
            log.add(e);
            
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
     
        return log;

    }
         public login login() {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/dev/web/app_dev.php/karim");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                try {
                    login = ser.parseLogin(new String(con.getResponseData()));
                } catch (IOException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return login.get(0);
    }
 
 
  public ArrayList<login> parseLogin(String json) throws IOException {

        ArrayList<login> log = new ArrayList<>();

        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
        for (Map<String, Object> obj : list) {
            //Création des tâches et récupération de leurs données
            login e = new login();
            
            float id = Float.parseFloat(obj.get("id").toString());
            
            e.setId((int) id);
             
             
             float id_user = Float.parseFloat(obj.get("idUser").toString());
            
            e.setId_user((int)id_user);
           
             e.setMail(obj.get("mail").toString());
             e.setUsername(obj.get("username").toString());
            
           
             
            
            log.add(e);
           currentUser=e; 
        }
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
     
        return log;

    }
  
  
  public boolean connexion(String pass,String username)
  
  {

       final UpdatableBCrypt s2= new UpdatableBCrypt(10);
     ArrayList<FosUser>  l2=users();
      
           for (FosUser u : l2)
           {
 String pwd=convert(u.getPassword());
  
                   if  ( s2.verifyHash(pass,pwd) && username.equals(u.getUsername()))
            {
                
                return true;
            }
                   
               }

   return false;  
}
    
  
  public String convert(String pass)
  {
       if (pass.startsWith("$2a"))
               {
                   return pass;
               }
  else 
               {
                   String strCopy1 = (pass.substring(3));
                  return "$2a"+strCopy1;
               }
  }
  
    public ArrayList<FosUser> parseFos(String json,String username) throws IOException {

       
FosUser e = new FosUser();
        
        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
        
        
        for (Map<String, Object> obj : list) {
            //Création des tâches et récupération de leurs données
           
            
            if  (obj.get("username").toString().equals(username))
            {
                 float id = Float.parseFloat(obj.get("id").toString());
            e.setId((int) id);
            
            e.setEmail(obj.get("email").toString());
            e.setUsername(obj.get("username").toString());
           e.setPassword(obj.get("password").toString());
                    allusers.add(e);
                
           }

           }
           return allusers;
        }
 
  
  
  public ArrayList<FosUser> FosUser2(String username) {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/dev/web/app_dev.php/login3/"+username);  
        System.out.println(con.getUrl());
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                try {
                    allusers = ser.parseFos(new String(con.getResponseData()),username);
                } catch (IOException ex) {
                   
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return allusers;
    }

    
 
      
}
