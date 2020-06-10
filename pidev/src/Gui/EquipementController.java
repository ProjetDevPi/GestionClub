/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.ClubController.E_cap_selection;
import static Gui.ClubController.E_id_selection;
import static Gui.ClubController.E_nom_selection;
import static Gui.ClubController.EditTable;
import static Gui.ClubController.MainStage;
import com.jfoenix.controls.JFXComboBox;
import entities.Club;
import entities.Equipement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import static jdk.nashorn.internal.objects.NativeJava.type;
import services.ClubService;
import services.EquipementService;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author User
 */
public class EquipementController implements Initializable {
   
    private Connection con;

    private TableColumn<Integer, Equipement> colum_cap;
    private PreparedStatement pst;
    private ResultSet rs;
    private MyConnection conn ;
    
    @FXML
    private TableView<Equipement> Table_equipement;

    @FXML
    private TableColumn<String , Equipement> colum_id;

    @FXML
    private TableColumn<Integer , Equipement> colum_ideq;

    @FXML
    private TableColumn<String , Equipement> colum_nomeq;

    @FXML
    private TableColumn<String ,Equipement> colum_type;
    
    @FXML
    private TableColumn<String ,Equipement> colum_image;

    @FXML
    private TextField enom;

   @FXML
    private ComboBox<String> Trie_par;

    @FXML
    private Button btnback;

    @FXML
    private Button ajouter;

    @FXML
    private Button supprimer;

    @FXML
    private Button modifier;
    
    @FXML
    private TextField filechoose;
    
   @FXML
    private ComboBox<String> sponsor;

    @FXML
    private Button chooser;
    private Image image;
       EquipementService es = new EquipementService();
    FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    
    private ObservableList<Equipement> data;
    ObservableList<String> listS = null;
   
    public void affichage() throws SQLException{
        
        conn=MyConnection.getInstance();
    
     try {
            
            data = FXCollections.observableArrayList();
            String sql = "SELECT * FROM equipement";
            rs = conn.getCnx().createStatement().executeQuery(sql);

            while (rs.next()) {
            String a = rs.getString("nom_image");
            System.out.println(a);
            Image image = new Image("file:" + a + "", 70, 70, true, true);

            ImageView photo = new ImageView(image);
           // p.setPhoto(photo);                
//data.addAll(data);
                data.add(new Equipement(rs.getInt(2), rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(5),photo));
            }
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
        
       // colum_ideq.setCellValueFactory(new PropertyValueFactory<>("ideq"));
        colum_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        colum_nomeq.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colum_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        colum_image.setCellValueFactory(new PropertyValueFactory<>("photo"));

        
   
 
        Table_equipement.setItems(null);
        Table_equipement.setItems(data);
        
    }
    
   
    
    @FXML
    private void Ajouter(Event event) throws ClassNotFoundException, SQLException {
        Equipement e = new Equipement();
        
        String nom = enom.getText();
        String file=filechoose.getText();
        String r = sponsor.getSelectionModel().getSelectedItem().toString();
        String t = Trie_par.getSelectionModel().getSelectedItem().toString();  
        EquipementService es = new EquipementService();
         int idU=es.findbynom(r); 
        e.setIdclub(idU);
        e.setNom(nom);
        e.setNom_image(file);
        e.setType(t);
      
        es.ajouterEquipement(e);affichage();
        

    }

    private void remplirComboBox() {
        String list[] = {"Sportif", "Educatif" , "Culturel","de vie", "protection individuelle"};

        for (int i = 0; i < list.length; i++) {
            Trie_par.getItems().add(list[i]);
        }
    }
    
    @FXML
    void back(ActionEvent event) {

    }

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
    void supp(ActionEvent event) throws SQLException {
               conn = MyConnection.getInstance();
        int selectedIndex = Table_equipement.getSelectionModel().getSelectedIndex();
        Equipement e = (Equipement) Table_equipement.getSelectionModel().getSelectedItem();
        EquipementService es=new EquipementService();
               es.supprimer(e.getIdeq());
        if (selectedIndex >= 0) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + e.getNom()+ " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
               
                Table_equipement.getItems().remove(selectedIndex);

            }

        }
    }
    
    public static Stage MainStage;
      public static int E_id_selection;
      public static String E_nom_selection;
      public static String E_type_selection;
      public static String EditTable = ""; 


    @FXML
    void update(Event event) throws SQLException, IOException {   Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) event.getSource()).getText();
        if (EditTable.equals("modifier")) { //tester sur lenomdu bouttom
            E_id_selection = Table_equipement.getSelectionModel().getSelectedItem().getIdeq();//recuperer l'id de la ligne sur la quelle on a appuie
            E_nom_selection = Table_equipement.getSelectionModel().getSelectedItem().getNom();//recuperer le nomde la ligne sur la quelle on a appuie
            E_type_selection = Table_equipement.getSelectionModel().getSelectedItem().getType();
         
        }
       
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("ajouterequipement.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
     stage2.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       remplirComboBox();  
        try {
            listS = es.getSponsor();
        } catch (SQLException ex) {
            Logger.getLogger(EquipementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            
        sponsor.setItems(listS);
        try {
            affichage();
             conn =MyConnection.getInstance();
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }    
    
}
