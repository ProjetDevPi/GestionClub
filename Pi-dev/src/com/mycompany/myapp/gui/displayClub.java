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
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
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
import com.mycompany.myapp.services.ServiceClub;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Mehdi
 */
public class displayClub {
     Form current;
     public static int idu=3;
    public static int idl=0;
        EncodedImage enc;
       Image imgs;
    ImageViewer imgv;
    Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
//h.add(lb);
    
  public displayClub(Resources res) {
       hi.getToolbar().addCommandToLeftBar("back", null, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
                new Menu().show();
         }
     });
        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Club", "Title")
                )
        );
      
        TextField search=new TextField();
//       hi.getToolbar().addCommandToRightBar("", res.getImage(""), e -> {});
        ServiceClub serviceTask = new ServiceClub();
     List<Club> list = serviceTask.getListClub();
          for (Club l:list)
      {
         
hi.add(createRankWidget(l,l.getIdclub(),l.getNomclub(), l.getCap(),res));
 hi.showBack();
    }
  }

   
          public SwipeableContainer createRankWidget(Club l,int id,String name, int auteur,Resources res) {
            MultiButton button = new MultiButton(name);  
            Button reserver = new Button("Commender");
        //add(reserver);
        button.setHeight(100);
        //button.setIcon(Image);
        button.setTextLine1(name);
        button.setTextLine2(""+auteur);
       // button.setTextLine3("" + price);
         //button.setTextLine4(Contenu);
         button.addActionListener(e->{
             if(l.getCap()!=0)
             { ParticiperClub a = new ParticiperClub(l,res);}
             else{Dialog.show("Désoler", "Pas de place", new Command("OK"));
                     }
             //dialog(l,res);
         });
        reserver.addActionListener(e -> {
           // System.out.println(idl);
           // Commande ser = new Commande();
           // ServiceCommande se= new ServiceCommande();
          //  se.Commender(id);
            Dialog.show("Sign In", "your book "+name+"has been ordered", "ok", null);
        });
    return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()), 
            button);
}
private Slider createStarRankSlider() {
    Slider starRank = new Slider();
     return starRank;
}
   private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
       private void dialog(Club e,Resources res) {
        Dialog d = new Dialog(e.getNomclub());
        String img=e.getNom_image();
            String url="http://localhost/dev/web/imageClub/"+img;
         try {
             enc = EncodedImage.create("/load.png");
         } catch (IOException ex) {
             //Logger.getLogger(displayClub.class.getName()).log(Level.SEVERE, null, ex);
         }
             imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL);
    
         imgv = new ImageViewer(imgs);
        TextArea popupBody = new TextArea( e.getCap()+ "\n"   , 8, 12);
        popupBody.setUIID("Label");
        popupBody.setEditable(false);
        Button b = new Button("test");
        d.setLayout(new BorderLayout());
        d.addComponent(BorderLayout.CENTER, popupBody);
 d.addComponent(BorderLayout.NORTH, imgv);
      //  d.add(BorderLayout.SOUTH,imgv);
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createFade(300));
        Rectangle rec = new Rectangle();
        rec.setX(700);
        rec.setY(1000);
        d.showPopupDialog(rec);
    }
        private void notif()
  {
         LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("your book has been added to your Order list");
        n.setAlertTitle("Order added!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
  }
    
   
      protected boolean isCurrentInbox() {
        return false;
    }
    protected boolean isCurrentTrending() {
        return false;
    }
    protected boolean isCurrentCalendar() {
        return false;
    }
    protected boolean isCurrentStats() {
        return false;
    }
    
     public Form getF() {
        return current;
    }
}