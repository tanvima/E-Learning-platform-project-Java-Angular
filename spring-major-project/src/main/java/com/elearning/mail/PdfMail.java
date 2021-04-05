package com.elearning.mail;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class PdfMail {
         public  void sendEmail(String to, String path,String coursename) throws AddressException, MessagingException, IOException {
        
                                 
        
        Properties props = new Properties();
        
        props.put("mail.smtp.host", "172.27.172.202");
        props.put("mail.from","dadaramj@evolvingsols.com");
        props.put("mail.smtp.ssl.trust", "172.27.172.202");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
//       props.put("mail.smtp.port", "25");



       Session session = Session.getInstance(props,
               new javax.mail.Authenticator() {
           protected PasswordAuthentication getPasswordAuthentication() {
               return new PasswordAuthentication("dadaramj@evolvingsols.com", "Tiger@321");
           }
       });



       Message message = new MimeMessage(session);
       message.setFrom(new InternetAddress("dadaramj@evolvingsols.com"));
       message.setRecipients(Message.RecipientType.TO,
               InternetAddress.parse(to));
       message.setSubject("Cybage-ELearning Course Completion Certificate");
       
//       //pdf 
//       Multipart multipart = new MimeMultipart();
//       MimeBodyPart attachPart = new MimeBodyPart();
//       String attachFile = "D:/java-major-backend/java-majorr-G6/unam eqqqreact.pdf";
//       attachPart.attachFile(attachFile);
//       multipart.addBodyPart(attachPart);
//
//
//       // sets the multi-part as e-mail's content
//       message.setContent(multipart);
// 
//       message.setText("cert");
//       
//       Transport.send(message);
       
       BodyPart messageBodyPart = new MimeBodyPart(); 
       String body = "Greeting from Cybgae-ELearning!! Here is your certificate for successful completion of the course "+coursename;
       messageBodyPart.setText(body);
       MimeBodyPart attachmentPart = new MimeBodyPart();
       attachmentPart.attachFile(new File("C:\\Users\\tanvima\\Desktop\\Java Major\\Certificate\\"+path+".pdf"));
       Multipart multipart = new MimeMultipart();
       multipart.addBodyPart(messageBodyPart);
       multipart.addBodyPart(attachmentPart);
       message.setContent(multipart);
       Transport.send(message);
       
       System.out.println("Sent message successfully....");
   }

}
