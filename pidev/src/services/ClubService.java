/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Club;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import utils.MyConnection;

/**
 *
 * @author User
 */
public class ClubService {

    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private MyConnection conn;

    @FXML
    private TableView<Club> Table_club;

    public ClubService() {
        cnx = MyConnection.getInstance().getCnx();
    }

    public void edit(int quantitee, int idd) throws SQLException {

        pre = cnx.prepareStatement("update club set cap=? where idclub=?");

        pre.setInt(1, quantitee);
        pre.setInt(2, idd);

        pre.executeUpdate();

    }

    public Club Clubfindbyid(int idd) {   //trajjaa lclub eli aandou el id passserenan parame -> membreparticiper
        Club p = new Club();
        try {

            String req = "SELECT * FROM club";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                if (res.getInt(1) == idd) {
                    p.setIdclub(idd);

                    p.setNom_image(res.getString("nom_image"));
                    p.setNomclub(res.getString("nomclub"));
                    p.setCap(res.getInt("cap"));

                }
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return p;
    }

    public ObservableList<Club> getMeals() throws SQLException {

        String req = "select * from club";
        st = cnx.createStatement();
        ResultSet result = st.executeQuery(req);

        ObservableList<Club> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
            Club p = new Club();
            String a = result.getString("nom_image");
            System.out.println(a);
            Image image = new Image("file:" + a + "", 70, 70, true, true);

            ImageView photo = new ImageView(image);
            p.setPhoto(photo);
            p.setIdclub(result.getInt("idclub"));
            p.setNomclub(result.getString("nomclub"));
            p.setCap(result.getInt("cap"));
            p.setNom_image(result.getString("nom_image"));

            System.out.println(p.toString());

            mealsList.add(p);

        }
        return mealsList;

    }

    public void ajouterClub(Club c) {

        try {
            String req = "INSERT INTO club (nomclub, cap, nom_image) VALUES "
                    + "('" + c.getNomclub() + "', '" + c.getCap() + "', '" + c.getNom_image() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

//    public void supprimer(int id) throws SQLException {
//
//        String sql = "DELETE FROM club WHERE idclub = ?";
//        PreparedStatement prst = cnx.prepareStatement(sql);
//        prst.setInt(1, id);
//        prst.executeUpdate();
//    }

    public void supprimer(int id) {
        try {
            String sql = "delete from club WHERE idclub = ?";
            PreparedStatement pstmt = cnx.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Delete Produit Done!");
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Update(Club P) throws SQLException {

        pre = cnx.prepareStatement("update club set nomclub=?,cap=? where idclub=?");

        pre.setString(1, P.getNomclub());
        pre.setInt(2, P.getCap());
        pre.setInt(3, P.getIdclub());

        pre.executeUpdate();

    }
}
