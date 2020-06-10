/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author User
*/

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.Membre;
import com.mycompany.myapp.entities.eleve;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.services.ServiceClub;
import com.mycompany.myapp.services.ServiceMembre;
import com.mycompany.myapp.services.ServiceUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public class displayClubMembre extends Form {
     public displayClubMembre(Form previous) {
       
             ServiceMembre serviceTask = new ServiceMembre();
            ArrayList<Membre> lis = serviceTask.getAllMyevent();
               for(Membre t : lis)
            {
        setTitle("Membre List");
     ServiceUser s2 = new ServiceUser();
           login l = new login();
           l=s2.login();
           ServiceMembre s=new ServiceMembre();
           Club c=new Club();
           c=s.getevent(t.getIdclub());
          
           Label desc=new Label("Mr:"); Label phone=new Label(""+l.getUsername());
                   // SpanLabel phone = new SpanLabel(l.getUsername()+"");
                   Label desc2=new Label("Club:"); Label email = new Label(""+c.getNomclub());
                   
       Button part=new Button("Supprimer");
        part.addPointerReleasedListener(new ActionListener() {
                         @Override
                         public void actionPerformed(ActionEvent evt) {
                             s.supprimerpart(t.getId());
                        
                     Dialog.show("Success","Participation Annulee",new Command("OK"));
                   s.getAllMyevent();
                            

                         }
                     });
add(desc);
        add(phone);
        add(desc2);
        add(email);
        add(part);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e->{ new Menu().show();});
    }
    }
}