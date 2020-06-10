package Gui;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;




public class UIController implements Initializable {

@FXML
private JFXButton btnconges;

    @FXML
    private JFXButton eq;

    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane parent;
    @FXML
    private JFXButton btnconges111;

            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }

 @FXML
    void club(Event event) throws IOException {
        
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Club.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();

    }
    
    @FXML
    void equipement(ActionEvent event) throws IOException {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Equipement.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
    }
    
      @FXML
    void membre(ActionEvent event) throws IOException {
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("ListeMembre.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();

    }
    
    @FXML
    void CONGE(ActionEvent event) throws IOException{
        try {
               
               
     
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
           } catch (IOException ex) {
              
           }
                   
    
                          ((Node) event.getSource()).getScene().getWindow().hide();
        

    }

    }
             
    




    

