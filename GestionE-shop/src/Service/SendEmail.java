package Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
 
public class SendEmail {
 
 final String senderEmail = "nizar.mejri@esprit.tn";
 final String senderPassword = "28526044";
 final String emailSMTPserver = "smtp.tunet.tn";
 final String emailServerPort = "25";
 final String receiverEmail = "nassim.gastli@esprit.tn";
 String emailSubject = null;
 String emailBody = null;
 
 public SendEmail(String receiverEmail, String Subject, String message) {
       // this.receiverEmail = receiverEmail;
        this.emailSubject = Subject;
        this.emailBody = message;
 
        Properties props = new Properties();
        props.put("mail.smtp.user", senderEmail);
        props.put("mail.smtp.host", emailSMTPserver);
        props.put("mail.smtp.port", emailServerPort);
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.socketFactory.port", emailServerPort);
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
  
 
        SecurityManager security = System.getSecurityManager();
        
     System.out.println("sds");
        try {
             Authenticator auth = new SMTPAuthenticator();
             Session session = Session.getInstance(props, auth);
 
             Message msg = new MimeMessage(session);
             msg.setText(emailBody);
             msg.setSubject(emailSubject);
             msg.setFrom(new InternetAddress(senderEmail));
             msg.addRecipient(Message.RecipientType.TO,
                               new InternetAddress(this.receiverEmail));
             Transport.send(msg);
             System.out.println("send successfully");
       } catch (Exception ex) {
             System.err.println("Error occurred while sending.!");
       }
 
 }
 
 private class SMTPAuthenticator extends javax.mail.Authenticator {
 
         public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
         }
 }
}
/*public void SendEmail(String email) {
      
           
            String host = "smtp.gmail.com";
            String user = "nassim.gastli@esprit.tn";
            String pass = "doliprex@fervex";
            String to = email;
            String from = "nassim.gastli@esprit.tn";
            String messageText = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account = " + code + " ";
            String subject = "Fixit Email Confirmation ";
            boolean sessionDebug = false;

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
            msg.setSentDate(new Date());
            msg.setText(messageText);

            javax.mail.Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message sent successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }*/