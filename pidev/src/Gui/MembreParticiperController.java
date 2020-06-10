/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXTextField;
import static com.qoppa.pdf.b.oc.aj;
import entities.Club;
import entities.Membre;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import services.ClubService;
import services.MembreService;
import services.NewClass;
import services.UserSevice;

/**
 * FXML Controller class
 *
 * @author User
 */
public class MembreParticiperController implements Initializable {

   
    @FXML
    private ComboBox<String> comboE;
    ObservableList<String> listE= null;
    UserSevice us=new UserSevice();
    MembreService ms =new MembreService();
     @FXML
    private JFXTextField namedetail;
    @FXML
    private TableView<Club> Table_club;
    @FXML
    private TableColumn<?, ?> photo;
    @FXML
    private TableColumn<String, Club> colum_name;
    @FXML
    private TableColumn<Integer, Club> colum_cap;
    @FXML
    private Button ajouter;
        
    public void detail()
        {Table_club.setOnMouseClicked((MouseEvent e) -> {
            Club p = Table_club.getItems().get(Table_club.getSelectionModel().getSelectedIndex());
           namedetail.setText(p.getNomclub());
           
        });
        }
    
    @FXML
    void ajouter(ActionEvent event) throws SQLException, MessagingException {
        
        
       String nomclub=namedetail.getText();
       String eleve=comboE.getSelectionModel().getSelectedItem().toString();
     
       MembreService ms=new MembreService();
       int ideleve=ms.findbyeleve(eleve);
       int idclub=ms.findbynom(nomclub);
         UserSevice s1=new UserSevice();
       String ss= s1.getlogin() ;                 //prendre l id du user connecté
       int result = Integer.parseInt(ss);
               ClubService cs=new ClubService();
        String mail=ms.getloginmail();
        System.out.println(mail);
       Club c=cs.Clubfindbyid(idclub);
       int cap =c.getCap();
       if (cap>0)
       {
        Membre m=new Membre();
        m.setId_user(result);
        m.setIdclub(idclub);
        m.setIdeleve(ideleve);
        int newcap=c.getCap()-1;            
        NewClass nc= new NewClass(mail);
Alert alert = new Alert(Alert.AlertType.INFORMATION, " Un mail de confirmation vous a ete envoyes ", ButtonType.OK);
            alert.showAndWait();
       
        cs.edit(newcap, c.getIdclub());
       ms.addContrat(m);
       
       }
       else{  Alert alert = new Alert(Alert.AlertType.ERROR, " IL N'Y A PLUS DE PLACE DANS CE CLUB ", ButtonType.OK);
            alert.showAndWait();}


    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       detail();
        try {
            String id =us.getlogin();
            System.out.println(id);
             int result = Integer.parseInt(id);
            listE=ms.getUser(result);
            System.out.println(listE);
        } catch (SQLException ex) {
            Logger.getLogger(MembreParticiperController.class.getName()).log(Level.SEVERE, null, ex);
        }
                comboE.setItems(listE);
       ObservableList<Club> listC=null;
       ClubService cs = new ClubService();
        try {
            listC=cs.getMeals();
        } catch (SQLException ex) {
            Logger.getLogger(MembreParticiperController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        colum_name.setCellValueFactory(new PropertyValueFactory<>("nomclub"));
        colum_cap.setCellValueFactory(new PropertyValueFactory<>("cap"));
   
               Table_club.setItems(listC);
    } 
    
    @FXML
    void home(ActionEvent event) throws IOException {
          Parent AnchorPane = FXMLLoader.load(getClass().getResource("Home.fxml"));
               Stage stage = new Stage();
               Scene scene = new Scene(AnchorPane);
               stage.setScene(scene);
               stage.show();
            
    
                          ((Node) event.getSource()).getScene().getWindow().hide();

    }
    
}
