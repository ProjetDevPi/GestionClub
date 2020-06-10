/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.FosUser;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author wejdene
 */
public class FXMLLoginController implements Initializable {
       @FXML
    private ImageView butterfly;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button btnlogin;
    
    

    @FXML
    void signup(Event event) {

    

        try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("FXMLRegister.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Register");
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }


    @FXML
    void login(ActionEvent event) throws SQLException {
        
        
  UserSevice s1=new UserSevice();
  s1.deletelogin();
       FosUser u1=s1.existLogin(username.getText(), password.getText());
       
     
       if (u1 != null)
       {
         s1.ajouterlogin(u1);
       String ss= s1.getlogin() ;
       int result = Integer.parseInt(ss);			
			
                        System.out.println(result);

        
           }      
       else 
       {
           System.out.println("Invalid Cred");
       }
                  
               
           try {
               if(u1.getRoles().equals("a:1:{i:0;s:11:\"ROLE_PARENT\";}"))
               {
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
                stage.setTitle("Home");
               }
               else if(u1.getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}"))
                   {
               
              
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("UI.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               
               stage.show();
                stage.setTitle("Plateforme Admin");
               }
                   
           } catch (IOException ex) {
               Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }    
    
}
