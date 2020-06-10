/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.Calendar;
import entities.FosUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MyConnection;


/**
 *
 * @author wejdene
 */
public class UserSevice {
        private Connection cnx;
    private Statement st;
    private PreparedStatement pre;
     UpdatableBCrypt s2 = new UpdatableBCrypt(10);

    public UserSevice() {
            cnx = MyConnection.getInstance().getCnx();
    }
    
    
    
        public void ajouterUser(FosUser u) {
        try {
         String e=u.getUsername();
           String em=u.getEmail();
            u.setUsername_canonical(e);
                u.setEmail_canonical(em);
          String  can=u.getUsername_canonical();
               String  mail=u.getEmail_canonical();
               
        u.setEnabled(1);
        int en=u.getEnabled();
               System.out.println(01);
            java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            u.setLast_login(date);
               System.out.println(02);
           
            
            
            String req = "INSERT INTO `fos_user`(`username`,`username_canonical`, `email`,`email_canonical`,`enabled`,`password`,`last_login`, `roles`) "
                    + "VALUES ('"+u.getUsername()+"','"+can+"','"+u.getEmail()+"','"+mail+"','"+en+"','"+u.getPassword()+"','"+u.getLast_login()
                    +"','"+u.getRoles()+"') ";
            
               System.out.println(03);
            
          st = cnx.createStatement();

            st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
          public FosUser existLogin(String username, String password) {
             
        
                Cryptage Cryptage = new Cryptage("lv39eptlvuhaqqsr");
        FosUser user = new FosUser();
        user=null;
        try {
            String req = "select * from fos_user where username =?  ";
            PreparedStatement preparedStatement;

            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString(8));
                System.out.println( s2.verifyHash(password,resultSet.getString(8)));
                System.out.println((password));
                try {
                   
                    if ( s2.verifyHash(password,resultSet.getString(8)) && (resultSet.getString(2).equals(username) )) {
                        
                        
                        user = new FosUser(resultSet.getInt("id"),resultSet.getString("username"),resultSet.getString("email"), password, resultSet.getString("roles"));
                       
                    }
                } catch (Exception ex) {
                    Logger.getLogger(UserSevice.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
               
                user = null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserSevice.class.getName()).log(Level.SEVERE, null, ex);
        }
       return user;
    }
            
            public void ajouterlogin(FosUser u) {
        if (u.getId()!=0 && u.getEmail()!=null && u.getUsername()!=null)
        {
            
                try { 
         String e=u.getUsername();
           String em=u.getEmail();
           int id_user=u.getId();
           
                
               
       
           
            
            
            String req = "INSERT INTO `login`(`id_user`,`username`,`mail`) "
                    + "VALUES ('"+id_user+"','"+e+"','"+em+"') ";
            
             
            
          st = cnx.createStatement();

            st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            }
             
            public void deletelogin() {
        try {
         
                
               
        
 
            
            String req = "DELETE FROM `login` ";
            
             
            
          st = cnx.createStatement();

            st.executeUpdate(req);

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
            
            /////////////////////get login ////////////
            public String getlogin() throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        int i=0;
        
        String req="SELECT id_user FROM `login` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("id_user");
        } 
         return pseudoL;
            }
            
        //retourne liste  des  noms utilisateurs    
     public List<String> listeuser() {

        List<String> listC = new ArrayList<>();

        try {

            String req = "SELECT * FROM fos_user  ";

            st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);

            while (res.next()) {
                
                {
                String p=null;
               p=res.getString("username");
               

                listC.add(p);
            }
            }
            
            

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return listC;
    }
     
     
      ///////////////////////////Le nom de l'utilisateurrrrrrrrr Connectéééé ////////////////
   
   public String  getloginusername() throws SQLException {
       
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        int i=0;
        
        String req="SELECT username FROM `login` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("username");
        } 
         return pseudoL;
            }
            
//////////////////////////////NOTIFICATION mAYSSAA//////////////
    public String getnotification() throws SQLException {
           ResultSet rs;
       
        
        st=cnx.createStatement();
        String pseudoL = null;
        int i=0;
        
        String req="SELECT username FROM `login` ";
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         pseudoL= rs.getString("username");
        } 
         return pseudoL;
            }
            
            ////////////////////mayssaa reclamation/////////
    
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
  
   public int getloginint() throws SQLException {
          ResultSet rs;
       
        st=cnx.createStatement();
       
        int i=0;
        
        String req="SELECT id_user FROM `login` ";
        int id;
        rs=st.executeQuery(req);
         while (rs.next()) {
         
         return id= rs.getInt("id_user");
        } 
         return 0;
            }
}
