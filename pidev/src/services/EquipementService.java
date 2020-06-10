/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Equipement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyConnection;

/**
 *
 * @author User
 */
public class EquipementService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private MyConnection conn ;
    
public EquipementService() {
    cnx = MyConnection.getInstance().getCnx();
    }

public void ajouterEquipement(Equipement e) {

        try {
            String req = "INSERT INTO equipement (idclub,nom, type, nom_image) VALUES "
                    + "('" + e.getIdclub() + "', '" + e.getNom() + "', '" + e.getType() + "', '"+ e.getNom_image() + "')";

            st = cnx.createStatement();

            st.executeUpdate(req);

            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
 public int findbynom(String name) throws SQLException // traaja3li el id mtaa3 el club eli 3aaditlou el nom mte3ou en parametre -> membreparticiper
{
    
String req = "SELECT * FROM club";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getString("nomclub").equals(name))
                {
String val=res.getString("idclub");
System.out.println(val);
int valeur= Integer.parseInt(val);  

return valeur;
                }
}
          
        int valeur=0;
          return valeur;
}

public  ObservableList<String> getSponsor() throws SQLException { //trajjaa les noms mtaa les club fi liste -> equipementcont (combobox)
     
     
        String req = "select nomclub from club";
        
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<String> List = FXCollections.observableArrayList();
    
        while (result.next()) {
                
         String    n=  result.getString("nomclub");
          
   
         List.add(n);
           
        }
        return List;
    
      }

public void supprimer(int id) throws SQLException{
        
    String sql = "DELETE FROM equipement WHERE ideq = ?";
        PreparedStatement prst = cnx.prepareStatement(sql);
                prst.setInt(1, id);
                prst.executeUpdate();
    }
    
public void Update(Equipement P) throws SQLException {
   
                    pre = cnx.prepareStatement("update equipement set nom=?,type=? where ideq=?");

                    pre.setString(1, P.getNom());
                    pre.setString(2, P.getType());
                    pre.setInt(3,P.getIdeq());
                   
                    pre.executeUpdate();
   
       
        }


}
