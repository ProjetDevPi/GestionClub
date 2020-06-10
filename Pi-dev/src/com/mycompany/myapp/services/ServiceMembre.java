/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.eleve;
import com.mycompany.myapp.entities.login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.io.ConnectionRequest;
import com.codename1.l10n.ParseException;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.Membre;



/**
 *
 * @author User
 */
public class ServiceMembre {
    ServiceUser s1 = new ServiceUser();
 public ArrayList<eleve> eleve;
    private ConnectionRequest req;

    public ServiceMembre() {
         req = new ConnectionRequest();
    }
    
        public void Participer(int id,int ide) {
        MultipartRequest con = new MultipartRequest();// création d'une nouvelle demande de connexion
      
        String Url = "http://localhost/dev/web/app_dev.php/cl/newmobile/" + "idclub=" + id+ "&idUser=" + ServiceUser.currentUser.getId()+"&ideleve="+ide;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((ee) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
        /////////////////////////////////////////////////////////////////
         ArrayList<eleve> eleves = new ArrayList<eleve>();
        public  ArrayList<eleve> getenfants(){

               login l =new login();
             l=s1.login();
        String url = "http://localhost/dev/web/app_dev.php/cl/enfant/"+l.getId_user();
        req.setUrl(url);
             
        req.setPost(false);
             //System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                eleves = parseEleve(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
            
        return eleves;
        
    }
        
 public ArrayList<eleve> parseEleve(String jsonText){
        try {
            eleve=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            System.out.println(list);
            for(Map<String,Object> obj : list){
                eleve t = new eleve();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setNom(obj.get("nom").toString());
                eleve.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        //System.out.println(eleve);
        return eleve;
    }
        
 /////Afficher levent selon le user connecter
 public ArrayList<Membre> part;
    public ArrayList<Membre> getAllMyevent(){
      ServiceUser s1= new ServiceUser();               
 login l =new login();
              l=s1.login();
        String url = "http://localhost/dev/web/app_dev.php/cl/nom/"+l.getId_user();
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                part = parseParticipant(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return part;
    }
          
 public ArrayList<Membre> parseParticipant(String jsonText){
        try {
            part=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
             
            for(Map<String,Object> obj : list){
                
                 Membre p = new Membre();
              
                Map<String, Object> listRecupprod = null;
                  Map<String, Object> listRecup = null;

              Club c = new Club();
                if (obj.get("idclub") != null) {

                    listRecupprod = (Map<String, Object>) obj.get("idclub");

                    p.setIdclub((int) Float.parseFloat(listRecupprod.get("idclub").toString()));
                   
                   
                }
                if (obj.get("el") != null) {

                    listRecupprod = (Map<String, Object>) obj.get("el");

                   p.setEl((int) Float.parseFloat(listRecup.get("id").toString()));
                   
                   
                }
               
             
                float id = Float.parseFloat(obj.get("id").toString());
                p.setId((int)id);

                part.add(p);
                                             



                  
            
            }
            
        } catch (IOException ex) {
            
        }
        return part;
    }
///////////////////////////////hethy mtaa3 ele cluv
 public ArrayList<Club> clubs;
   ArrayList<Club> listClub = new ArrayList<>();
     public Club getevent(int id){
        String url = "http://localhost/dev/web/app_dev.php/cl/find/"+id;
        req.setUrl(url);
        req.setPost(false);
             System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listClub = ClubParseJson(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
//        System.out.println(clubs.get(0));
        return listClub.get(0);
    }   
     
    public ArrayList<Club> ClubParseJson(String json)  {

       

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");

            for (Map<String, Object> obj : list) {

                Club e = new Club();
               // float id = Float.parseFloat(obj.get("idExperience").toString());
              
                float id = Float.parseFloat(obj.get("idclub").toString());
                float cap = Float.parseFloat(obj.get("cap").toString());
                //float quantite = Float.parseFloat(obj.get("quantitelivre").toString());
                 e.setIdclub((int) id);
                //e.set((int) id);
                e.setNomclub(obj.get("nomclub").toString());
                e.setNom_image(obj.get("nomImage").toString());
                
                e.setCap((int) cap);
           
           
                 
                listClub.add(e);

            }

        } catch (IOException ex) {
        }
         //System.out.println(listLivre1);
        return listClub;
    }
    //////////////////////hethy mtaa3 el eeve
    
   ArrayList<eleve> listeleve = new ArrayList<>();
     public eleve getelevememebre(int id){
        String url = "http://localhost/dev/web/app_dev.php/cl/elevef/"+id;
        req.setUrl(url);
        req.setPost(false);
             System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                   eleve =  parseFind(new String(req.getResponseData()));
                } catch (IOException ex) {
                  
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
//        System.out.println(clubs.get(0));
        return eleve.get(0);
    }   
     
   
    ///////////////////////supppp
     public void supprimerpart(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String url = "http://localhost/dev/web/app_dev.php/cl/supprimerparticipant/" + id;
        System.err.println(url);
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con); //appel asynchrone

    }
     /////////////////////findidnom
   
     public eleve getNomEleve(String nom ){
        String url = "http://localhost/dev/web/app_dev.php/cl/findidnom/"+nom;
        req.setUrl(url);
        req.setPost(false);
             System.out.println(req.getUrl());
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    eleve = parseFind(new String(req.getResponseData()));
                } catch (IOException ex) {
                   
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        System.out.println(eleve.get(0));
        return eleve.get(0);
    }
      
       public ArrayList<eleve> parseFind(String json) throws IOException {

        ArrayList<eleve> eleve = new ArrayList<>();

        JSONParser j = new JSONParser();
        Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));
        List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
        for (Map<String, Object> obj : list) {
            //Création des tâches et récupération de leurs données
            eleve e = new eleve();
            
            float id = Float.parseFloat(obj.get("id").toString());
            
            e.setId((int) id);
             
             
             e.setNom(obj.get("nom").toString());
           
            
                 
            eleve.add(e);
            
        }
  
        return eleve;

    }

}
