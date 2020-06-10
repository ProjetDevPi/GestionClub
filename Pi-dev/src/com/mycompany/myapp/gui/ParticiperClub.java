/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Response;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
import com.mycompany.myapp.entities.Club;
import com.mycompany.myapp.entities.Membre;
import com.mycompany.myapp.entities.eleve;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.services.ServiceMembre;
import com.mycompany.myapp.services.ServiceUser;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author Mehdi
 */
public class ParticiperClub {
     Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
    EncodedImage enc;
       Image imgs;
    ImageViewer imgv;
 private Resources theme;
  eleve e1 = new eleve();
  String e3;
   ServiceMembre s2 = new ServiceMembre();
    public ParticiperClub(Club l,Resources res)
    {
     String img=l.getNom_image();
    String url="http://localhost/dev/web/imageClub/"+img;
        System.out.println(img);
        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label(l.getNomclub(), "Title")
                )
        );
         //installSidemenu(res);
              hi.getToolbar().addCommandToLeftBar("back", null, new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent evt) {
                displayClub a = new displayClub(res);
                a.hi.showBack();
         }
     });

        TextArea popupBody = new TextArea("\n" +"Capacite:"+ l.getCap()+"\n"  , 8, 12);
        popupBody.setEditable(false);
         try {
            enc = EncodedImage.create("/load.png");
                    } catch (IOException ex) {
        }
        
       /*/  e1=s2.getenfants(); //eleve
                ComboBox<eleve> enfants = new ComboBox<>(e1.getNom());/*/
            ComboBox<String> comboCat;
             comboCat = new ComboBox<String>();
        for (eleve item : s2.getenfants()) {
        comboCat.addItem(item.getNom());
        }
       ServiceUser s3=new ServiceUser() ;                  
        login lo =new login();
              lo=s3.login();
              int log=lo.getId_user();
                //e3=e1.getNom();//nom
         // enfants.getSelectedItem();
           //System.out.println(e1);
          //System.out.println(ide);con.setUrl("http://localhost/dev/web/app_dev.php/cl/newmobile/"+l.getIdclub()+"/"+log+"/"+log);
                            
        // Form f1 = new Form("",new BoxLayout(BoxLayout.Y_AXIS));
          Button reserver = new Button("Pariciper");
          String r= comboCat.getSelectedItem().toString();
            reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String nomenfant=comboCat.getSelectedItem();
                System.out.println(nomenfant);
                ServiceMembre m=new ServiceMembre();
                eleve e=new eleve();
                e=m.getNomEleve(nomenfant);
                System.out.println(e);
                       ConnectionRequest con = new ConnectionRequest();
                       con.setUrl("http://localhost/dev/web/app_dev.php/cl/newmobile/"+l.getIdclub()+"/"+log+"/"+e.getId()
                         
                         
                            );

                System.out.println(comboCat.getSelectedItem());
                    con.addResponseListener(new ActionListener<NetworkEvent>() {
                        @Override
                        public void actionPerformed(NetworkEvent evt) {

            String accountSID = "AC892a206731134e7459714551b791682a";
            String authToken = "d1d525fb47b878ebcd7037c36ae8129e";
            String fromPhone = "+12058786780";
            String destinationPhone = "+21623120085";

            Response<Map> SMS = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
                    queryParam("To", destinationPhone).
                    queryParam("From", fromPhone).
                    queryParam("Body", "Vous etes maintenant membre dans notre club ").
                    header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
                    getAsJsonMap();
        
                            System.out.println("Participation a été ajouté avec succées.");
                            
                            ToastBar.showMessage("Participation ajouté avec succées.",FontImage.MATERIAL_DONE);
                           

                        }
                    });

                    con.setFailSilently(true);
                    NetworkManager.getInstance().addToQueueAndWait(con);
                 
    displayClub a = new displayClub(theme);

a.hi.show();
                
                        }
                   
                 
            
        });
         
   
        imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE_TO_FILL);
         imgv = new ImageViewer(imgs);
        Container C1= new Container( new BoxLayout(BoxLayout.Y_AXIS));
        C1.add(popupBody);
        Container C2= new Container(new BoxLayout(BoxLayout.Y_AXIS));
        C2.add(imgv);
       hi.add(C2);
      hi.add(C1);
      hi.add(comboCat);
      hi.add(reserver);
       hi.show();   

       ShareButton sb = new ShareButton();
sb.setText("Share Club");
hi.add(sb);

Image screenshot = Image.createImage(hi.getWidth(), hi.getHeight());
hi.revalidate();
hi.setVisible(true);
hi.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    
}
sb.setImageToShare(imageFile, "file://C:/wamp64/www/dev/web/imageClub/natation.jpg");                                   
    
    

    }
    
    
  /*   public void installSidemenu(Resources res) {
  Toolbar tb = hi.getToolbar();   
  tb.addMaterialCommandToSideMenu("Inscription", FontImage.MATERIAL_HOME, e -> {}); 
tb.addMaterialCommandToSideMenu("Absence", FontImage.MATERIAL_WEB, e -> {
});
tb.addMaterialCommandToSideMenu("Carnets", FontImage.MATERIAL_SETTINGS, e -> {
});
tb.addMaterialCommandToSideMenu("Consulter Enfants", FontImage.MATERIAL_INFO, e -> {
});
tb.addMaterialCommandToSideMenu("Consulter Produits", FontImage.MATERIAL_HOME, e -> {}); 
tb.addMaterialCommandToSideMenu("Mes Achats", FontImage.MATERIAL_WEB, e -> {
});
tb.addMaterialCommandToSideMenu("Evenements", FontImage.MATERIAL_SETTINGS, e -> {
});
tb.addMaterialCommandToSideMenu("Documents", FontImage.MATERIAL_INFO, e -> {
          displayDocuments a = new displayDocuments(res);
           a.hi.show();
});
tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {});
   hi.getToolbar().addMaterialCommandToRightBar("logOut",FontImage.MATERIAL_LOGOUT,
                                        event->{
                                        new HomeForm().getF().show();
                                        });
//hi.addComponent(new Label("Hello "+l.getUsername()));
  hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("Inbox", "Title")
                )
        );
       // initGuiBuilderComponents(theme);
      hi.getToolbar().addCommandToRightBar("", theme.getImage("toolbar-profile-pic.png"), e -> {});
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
    }*/
}