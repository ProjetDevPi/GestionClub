/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Equipement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.EquipementService;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AjouterequipementController implements Initializable {

     private MyConnection conn;
    private MyConnection dc;
    private PreparedStatement pst;
    
    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField filechoose;

    
    @FXML
    private Button add;
    @FXML
    private Button btn_cancel;

    @FXML
    private TextField id;

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
    
    @FXML
    private void Modifier(Event event) throws ClassNotFoundException, SQLException, IOException {
      
    String nomeq = nom.getText();
    String typeq = type.getText();
    int idd=Integer.parseInt(id.getText());
    nom.setText(nomeq);
    id.setText(String.valueOf(idd));
        //prenom.setText(E_prenom_selection);
        Equipement e=new Equipement();
        e.setIdeq(idd);
        e.setNom(nomeq);
        e.setType(typeq);
   
     EquipementService ec=new EquipementService();
     ec.Update(e);
      Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("Equipement.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
     stage2.show();

    }

    @FXML
    private void cancel(ActionEvent event) throws IOException {
                                
            ((Node)event.getSource()).getScene().getWindow().hide();
            Stage s1=new Stage();
            FXMLLoader L=new FXMLLoader();
            Pane root=L.load(getClass().getResource("Equipement.fxml"));
            Scene c=new Scene(root);
            
            s1.setScene(c);
    }

   
   @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conn=MyConnection.getInstance();
         int id1= EquipementController.E_id_selection;

        id.setText(String.valueOf(id1));
        nom.setText(EquipementController.E_nom_selection);
       // prenom.setText(ClubCotroller.E_prenom_selection);
        type.setText(EquipementController.E_type_selection);
    }   
    

}
