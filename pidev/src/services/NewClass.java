/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;



import entities.Membre;
import java.sql.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author daami
 */
public class NewClass {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author AYOUB
 */

    
     
    public NewClass(String email) throws MessagingException {
    

   
try{

            String host = "smtp.gmail.com";
            String user = "karim.bentarjem.1@esprit.tn";
            String pass = "kk23120085";
            String to = email;
            String from = "karim.bentarjem.1@esprit.tn";
            MembreService ms=new MembreService();
            Membre m=new Membre();
          //  int idd = m.getId_user();
             String usernom =null;     
               UserSevice s1=new UserSevice();
               String ss= s1.getlogin() ;
              int result = Integer.parseInt(ss);
              usernom=ms.findbynom(result);
             //String nomeleve=null;
            // nomeleve=ms.findbynomeleve(m.getIdeleve());
            // System.out.println(nomeleve);
            String messageText = "Bonjour mr. "+usernom+" Votre fils/fille a ete inscris avec succées dans notre club ";
            String subject = "Confirmation pour une inscriptiion dans un club ";
            boolean sessionDebug = true;

            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
           // msg.setSentDate(new Date());
            msg.setText(messageText);

            javax.mail.Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message sent successfully");
    } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
    