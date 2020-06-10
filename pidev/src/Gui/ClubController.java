/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXTextField;
import entities.Club;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.ClubService;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ClubController implements Initializable {

    @FXML
    private Button add;
    ObservableList<Club> data = FXCollections.observableArrayList();
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
    @FXML
    private TableColumn<Club, String> photo;

    private Image image;
    FileChooser fc = new FileChooser();
    File selectedFile = new File("");
    // private ObservableList<Club> data;

    @FXML
    private TableView<Club> Table_club;
    @FXML
    private TableColumn<String, Club> colum_id;
    @FXML
    private TableColumn<String, Club> colum_name;
    @FXML
    private TableColumn<Integer, Club> colum_cap;
    private PreparedStatement pst;
    private ResultSet rs;
    private MyConnection conn;
    @FXML
    private Button btn_ajouter;
    @FXML
    private Button btn_modifier;
    @FXML
    private Button btn_supp;
//    @FXML
//    private ImageView imageview;
    ObservableList<Club> listC = null;
    ClubService cs = new ClubService();
    @FXML
    private ImageView imgview;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXTextField Recherche_Tf;

    @FXML
    void image(ActionEvent event) {
        fc.setTitle("Open Resource File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text files", ".pdf", ".tkt", ".docx", ".png", "*.jpg"));
        fc.setInitialDirectory(new File("C:"));
        selectedFile = fc.showOpenDialog(null);
//UploadFile.upload(selectedFile,"", "");
        File file = new File("" + selectedFile.getName());
        filechoose.setText(selectedFile.getName());

        //images.setImage(imagee);   
    }

    @FXML
    private void Ajouter(Event event) throws ClassNotFoundException, SQLException {
        Club c = new Club();

        String nom = nomclub.getText();
        int capacite = Integer.valueOf(cap.getText());
        String file = filechoose.getText();

        c.setNomclub(nom);
        c.setCap(capacite);
        c.setNom_image(file);

        ClubService cs = new ClubService();
        cs.ajouterClub(c);
        ObservableList<Club> data = FXCollections.observableArrayList();
        ClubService srvRec = new ClubService();
        data = FXCollections.observableArrayList(srvRec.getMeals());

        Table_club.setItems(data);

    }
    public static Stage MainStage;
    public static int E_id_selection;
    public static String E_nom_selection;
    public static int E_cap_selection;
    public static String E_image_selection;
    public static String EditTable = "";

    @FXML
    void modifier(Event event) throws SQLException, IOException {
        Stage stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        MainStage = stage1;
        EditTable = ((Button) event.getSource()).getText();
        if (EditTable.equals("Modifier")) { //tester sur lenomdu bouttom
            E_id_selection = Table_club.getSelectionModel().getSelectedItem().getIdclub();//recuperer l'id de la ligne sur la quelle on a appuie
            E_nom_selection = Table_club.getSelectionModel().getSelectedItem().getNomclub();//recuperer le nomde la ligne sur la quelle on a appuie
            E_cap_selection = Table_club.getSelectionModel().getSelectedItem().getCap();
            E_image_selection = Table_club.getSelectionModel().getSelectedItem().getNom_image();

        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        Parent root2 = FXMLLoader.load(getClass().getResource("ajouterclub.fxml"));
        Scene scene2 = new Scene(root2);
        Stage stage2 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage2.setScene(scene2);
        stage2.centerOnScreen();
        stage2.show();

    }

    @FXML
    void supprimer(ActionEvent event) throws SQLException {
        conn = MyConnection.getInstance();
        int selectedIndex = Table_club.getSelectionModel().getSelectedIndex();
        Club c = (Club) Table_club.getSelectionModel().getSelectedItem();
        ClubService s = new ClubService();
        s.supprimer(c.getIdclub());
        if (selectedIndex >= 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + c.getNomclub() + " ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                Table_club.getItems().remove(selectedIndex);

            }

        }
    }

//    @FXML
//    private void Rechercher(ActionEvent event) {
//
//        FilteredList<Club> filteredData = new FilteredList<>(data, e -> true);
//        Recherche_Tf.setOnKeyReleased(e -> {
//            Recherche_Tf.textProperty().addListener((observableValue, oldValue, newValue) -> {
//                filteredData.setPredicate((Predicate<? super Club>) (c) -> {
//                    if (newValue == null || newValue.isEmpty()) {
//                        return true;
//
//                    }
//                    String lowercasefilter = newValue.toLowerCase();
//                    if (c.getNomclub().toLowerCase().contains(lowercasefilter)) {
//                        return true;
//                    } else if (c.getNom_image().toLowerCase().contains(lowercasefilter)) {
//                        return true;
//                    }
//                    return false;
//
//                });
//
//            });
//            SortedList<Club> sortedData = new SortedList<>(filteredData);
//            sortedData.comparatorProperty().bind(Table_club.comparatorProperty());
//            Table_club.setItems(sortedData);
//        });
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Club> data = FXCollections.observableArrayList();
        ClubService srvRec = new ClubService();
        try {
            data = FXCollections.observableArrayList(srvRec.getMeals());
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            listC = cs.getMeals();
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colum_id.setCellValueFactory(new PropertyValueFactory<>("idclub"));
        colum_name.setCellValueFactory(new PropertyValueFactory<>("nomclub"));
        colum_cap.setCellValueFactory(new PropertyValueFactory<>("cap"));
        photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
        Table_club.setItems(listC);
        
        FilteredList<Club> filteredData = new FilteredList<>(data, b -> true);
		
		Recherche_Tf.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(Club -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Club.getNomclub().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				 
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		SortedList<Club> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table_club.comparatorProperty());
		Table_club.setItems(sortedData);

    }
    

}
