/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

/**
 *
 * @author ASUS
 */
public class ItemController  implements Initializable{

    @FXML
    private ImageView imageview1;

    @FXML
    private ImageView imageview2;
       @FXML
    private JFXButton back;


    @FXML
    void image(ActionEvent event) {
        imageview1.setVisible(false);
        imageview2.setVisible(true);

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageview2.setVisible(false);
    }
    
   
    @FXML
    void back(ActionEvent event) {
        imageview1.setVisible(true);
        imageview2.setVisible(false);

    }
    
}
