/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Club;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class ServiceClub {
     ArrayList<Club> listClub = new ArrayList<>();
   
    public ArrayList<Club> ClubParseJson(String json) throws ParseException {

        ArrayList<Club> listClub1 = new ArrayList<>();

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
           
           
                 
                listClub1.add(e);

            }

        } catch (IOException ex) {
        }
         //System.out.println(listLivre1);
        return listClub1;
    }
     
     public ArrayList<Club> getListClub() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/dev/web/app_dev.php/cl/showclubmobile");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceClub sl = new ServiceClub();
                try {
                    listClub = sl.ClubParseJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listClub;
    
    } 
}
