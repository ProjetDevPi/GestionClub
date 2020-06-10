/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.SwitchList;
import com.codename1.io.Log;
import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.callSerially;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.login;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.services.UpdatableBCrypt;
import java.io.IOException;
import java.util.Arrays;






/**
 *
 * @author bhk
 */
public class HomeForm  {

 Form current = new Form("S'authentifier", new BoxLayout(BoxLayout.Y_AXIS));;
 Label username = new Label();
    Label password;
    TextField tfusername;
    TextField tfpassword;
    Button btnLogin,btnaff,reset;
    final UpdatableBCrypt s2= new UpdatableBCrypt(10);
    public HomeForm(){ 
        

        login l=new login();
        ServiceUser s1= new ServiceUser();
        l=s1.login(); 
       
       
        System.out.println(l.getId_user());

         username = new Label("                          Username           ");
        
        //username.getSelectedStyle().setFgColor(ColorUtil.CYAN);
        tfusername= new TextField("","username", 10, 0);
         password = new Label("                          Password           ");
        tfpassword = new TextField("","password", 10, 0);
         tfpassword.setConstraint(TextField.PASSWORD);
       
        btnLogin = new Button("Login");
         reset = new Button("Annuler");
       
        
        current.add(username);
         current.add(tfusername);
         current.add(password);
         current.add(tfpassword);
         current.add(btnLogin);
      
        
      
        btnLogin.addActionListener((e) -> {
         if (s1.connexion(tfusername.getText(),tfpassword.getText()))
         {    Dialog.show("Success", "Les données sont Correctes", new Command("OK"));
             System.out.println("les données s7a7");
            System.out.println(s1.FosUser2(tfusername.getText())); 
            new Menu().show();
         }
         else {
             Dialog.show("Error", "Les données sont Incorrectes", new Command("OK"));
             System.out.println("les données ghaltin ");
         }
             
        });
    
     
       
        
        //current.add(new Label("Choose an option"));
       // Button btnAddTask = new Button("Add Task");
        //Button btnListTasks = new Button("List Tasks");
        
        //btnAddTask.addActionListener(e-> new addEleve(current).show());
        //btnListTasks.addActionListener(e-> {
            //try {
               // new ListTasksForm(current).show();
           // } catch (ParseException ex) {
              
           // }
        //});
       

         //current.addAll(btnAddTask,btnListTasks);
         current.show();
        
        
    }
    
    public Form getF() {
        return current;
    }

    }
                
