/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Membre;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.MembreService;
import utils.MyConnection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import static com.qoppa.pdf.b.oc.wb;
/**
 * 
 * FXML Controller class
 *
 * @author User
 */
public class ListeMembreController implements Initializable {

   @FXML
    private TableView<Membre> membre;

    @FXML
    private TableColumn<?, ?> parent;

    @FXML
    private TableColumn<?, ?> eleve;

    @FXML
    private TableColumn<?, ?> club;
    
    private ResultSet rs;
    private Connection cnx;
    private Statement pst;
    private PreparedStatement pre;
    private MyConnection conn ;
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private XSSFRow header;
     
  @FXML
    void supprimer(ActionEvent event) throws SQLException {
  conn = MyConnection.getInstance();
        int selectedIndex = membre.getSelectionModel().getSelectedIndex();
        Membre c = (Membre) membre.getSelectionModel().getSelectedItem();
      MembreService s=new MembreService();
               s.supprimer(c.getId());
        if (selectedIndex >= 0) {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete ?", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
               
                membre.getItems().remove(selectedIndex);

            }

        }
    }
    
    
    @FXML
    private void imprimer(ActionEvent event) {
        try {
         String query ="select * from membre";
cnx = MyConnection.getInstance().getCnx();
pre=cnx.prepareStatement(query);
        
        rs= pre.executeQuery();
        
        
         wb= new XSSFWorkbook();
        sheet= wb.createSheet("Liste des membres");
        header =sheet.createRow(0);
        header.createCell(0).setCellValue("parent");
        header.createCell(1).setCellValue("eleve");
        header.createCell(2).setCellValue("club");
        
        int index=1;
          
        while(rs.next()){
            Membre m=new Membre();
            MembreService ms=new MembreService();
            m.setParent(ms.findbynom(rs.getInt("id_user")));
            m.setClub(ms.findbynomclub(rs.getInt("idclub")));
            m.setEleve(ms.findbynomeleve(rs.getInt("ideleve")));

          XSSFRow row=sheet.createRow(index);
            row.createCell(0).setCellValue(m.getParent());
            row.createCell(2).setCellValue(m.getClub());
            row.createCell(1).setCellValue(m.getEleve());
            index++;
        }
        
       FileOutputStream fileOut= new FileOutputStream("membre2.xlsx");
        wb.write(fileOut);
        fileOut.close();
        
        Alert alert =new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Liste membre Exported in Excel Sheet.");
        alert.showAndWait();
        
        pre.close();
        rs.close();
        
    }
    catch (SQLException ex) {
           System.out.println("Controllers.ListeMembreController.imprimerListeMembre()");
        }
    catch (IOException ex) {
        System.out.println("Controllers.ListeMembreController.imprimerListeMembre()");
    }


    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  ObservableList<Membre> listP=null;
  MembreService ps = new MembreService();
       
        parent.setCellValueFactory(new PropertyValueFactory<>("parent"));
        eleve.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        club.setCellValueFactory(new PropertyValueFactory<>("club"));
        
       try {
           listP = ps.getMeals();
       } catch (SQLException ex) {
           Logger.getLogger(ListeMembreController.class.getName()).log(Level.SEVERE, null, ex);
       }

        membre.setItems(listP);   
    }    
    
}
