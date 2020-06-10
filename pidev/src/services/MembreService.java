/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Membre;
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
public class MembreService {
    private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
    private MyConnection conn;
    
        public MembreService() {
        cnx = MyConnection.getInstance().getCnx();
    }
        public  ObservableList<String> getUser(int idU) throws SQLException { // trajjaa les nom des enfants mtaa l'user connecte -> particpercont (combobox)
     
     
        String req = "select * from eleve";
        
           st = cnx.createStatement();
           ResultSet result = st.executeQuery(req);
        
        ObservableList<String> mealsList = FXCollections.observableArrayList();
    
        while (result.next()) {
          if(result.getInt("User_id")==idU){
                
         String    n=  result.getString("nom");
           
   
            mealsList.add(n);
           
        }
        }
        return mealsList;
    
      }
        public String findbynomclub(int user ) throws SQLException{ //trajjaa nom mtaa lclub elli aandou id passé en param -> listmembrecontroller
                  
String req = "SELECT * FROM club ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("idclub")==user)
                {
            
      String  name= res.getString("nomclub");
            return name;
              }
            }
            return null;
              }
         
        public String findbynomeleve(int user ) throws SQLException{ //traaja3leek esm leleve eli lid mte3ou passer en param -> listemembrecont
                  
String req = "SELECT * FROM eleve ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==user)
                {
            
      String  name= res.getString("nom");
            return name;
              }
            }
            return null;
              }
       
        public String findbynom(int user ) throws SQLException{//traja3lek esem el user eli l'id passé en parametre
                  
String req = "SELECT * FROM fos_user ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getInt("id")==user)
                {
            
      String  name= res.getString("username");
            return name;
              }
            }
            return null;
              }
        ////////////////////////////////////autrefonction/////////////////////
        public String getusername(int idd) throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        
        
        String req="SELECT * FROM fos_user ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         if( rs.getInt("id") ==idd)
             
         pseudoL= rs.getString("username");
        } 
         System.out.println(pseudoL);
         return pseudoL;
         
            }
//////////////////////////////////////////
        public String getloginmail() throws SQLException {
          ResultSet rs;
       
        st=cnx.createStatement();
        String pseudoL = null;
        int i=0;
        
        String req="SELECT mail FROM `login` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("mail");
        } 
         return pseudoL;
            }

        public int findbyeleve(String name) throws SQLException // traja3lek el id mtaa3 eseem el eleve passer en parametere
{
    
String req = "SELECT * FROM eleve";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            
            while (res.next()) {
                if (res.getString("nom").equals(name))
                {
String val=res.getString("id");

int valeur= Integer.parseInt(val);  

return valeur;
                }
}
          
        int valeur=0;
          return valeur;
}
        public  ObservableList<Membre> getMeals() throws SQLException { //affichage des membres
     
     
        String req = "select * from membre";
     st = cnx.createStatement();
            ResultSet result = st.executeQuery(req);
        
        ObservableList<Membre> mealsList = FXCollections.observableArrayList();
        while (result.next()) {
                Membre p = new Membre();

                p.setId(result.getInt("id"));
                p.setParent(findbynom(result.getInt("id_user")));
                p.setClub(findbynomclub(result.getInt("idclub")));
                
                p.setEleve(findbynomeleve(result.getInt("ideleve")));
               
System.out.println(p.toString());
          
           
            mealsList.add(p);
          
        }
        return mealsList;
    
      }
       
        public int findbynom(String nom ) throws SQLException{//traaja3lek lid mtaa3 el club eli esmou passer en parametre
                 
String req = "SELECT * FROM club ";
st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            while (res.next()) {
                if (res.getString("nomclub").equals(nom))
                {
           
                    int  name= res.getInt("idclub");
                   
                     return name;
                 }
            }
            return 0 ;
              }
        
        public void addContrat(Membre c) {

        try {
            String req = "INSERT INTO membre (idclub, ideleve,id_user) VALUES "
                    + "('" + c.getIdclub() + "', '" + c.getIdeleve() + "','" + c.getId_user() + "')";

            st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Insertion Reussie!");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }  
        public void supprimer(int id) throws SQLException{
        String sql = "DELETE FROM membre WHERE id = ?";
        PreparedStatement prst = cnx.prepareStatement(sql);
                prst.setInt(1, id);
                prst.executeUpdate();
    }

    }

