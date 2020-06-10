/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ClubController.E_cap_selection;
import static Gui.ClubController.E_id_selection;
import static Gui.ClubController.E_nom_selection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author User
 */
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Club;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ClubService;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author daami
 */
public class AjouterclubController implements Initializable {

    
    @FXML
    private Button add;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button btn_cancel;
    @FXML
    private TextField nomclub;
    @FXML
    private TextField cap;
    @FXML
    private TextField filechoose;
  
    @FXML
    private Button chooser;
    private Image image;
    FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    
    @FXML
    void image(ActionEvent event) {
      fc.setTitle("Open Resource File");
      fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files",".pdf", ".tkt", ".docx",".png","*.jpg"));
        fc.setInitialDirectory(new File("C:"));
        selectedFile = fc.showOpenDialog(null);
//UploadFile.upload(selectedFile,"", "");
        File file = new File("" + selectedFile.getName());
        filechoose.setText(selectedFile.getName());

        //images.setImage(imagee);   
    }
      
    private MyConnection conn;
    private MyConnection dc;
    private PreparedStatement pst; 
   @FXML
    private TextField id;
    @FXML
    private JFXTextField idC_tf;
 

    @FXML
    private void Modifier(Event event) throws ClassNotFoundException, SQLException, IOException {
      
String nom=nomclub.getText();
int capa=Integer.parseInt(cap.getText());
int idd=Integer.parseInt(id.getText());
nomclub.setText(nom);
id.setText(String.valueOf(idd));
        //prenom.setText(E_prenom_selection);
        cap.setText(String.valueOf(cap));
        Club c=new Club();
        c.setIdclub(idd);
        c.setNomclub(nom);
        c.setCap(capa);
   
     ClubService sc=new ClubService();
     sc.Update(c);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("Club.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
     stage2.show();

    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
                                
            
               Parent AnchorPane = FXMLLoader.load(getClass().getResource("Club.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn=MyConnection.getInstance();
         int id1= ClubController.E_id_selection;
             int capa= ClubController.E_cap_selection;

        id.setText(String.valueOf(id1));
        nomclub.setText(ClubController.E_nom_selection);
       // prenom.setText(ClubCotroller.E_prenom_selection);
        cap.setText(String.valueOf(capa));
    }   
    
}

    