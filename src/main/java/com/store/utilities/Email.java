package com.store.utilities;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author Abdullah
 */
public class Email {

    /**
     *
     * @param htmlMessage
     * @param address
     * @param subject
     */
    public void sendEmail(String htmlMessage,String address,String subject){
    	
    	  Properties props = System.getProperties();
    	    props.put("mail.smtp.starttls.enable", true);
    	    props.put("mail.smtp.host", "mail.pod-server.com");
    	    props.put("mail.smtp.user", "donotreply@pod-server.com");
    	    props.put("mail.smtp.password", "podserver@321");
    	    props.put("mail.smtp.port", "25");
    	    props.put("mail.smtp.auth", true);
            props.put("mail.smtp.ssl.trust", "mail.pod-server.com");



    	    Session session = Session.getInstance(props,null);
    	    MimeMessage message = new MimeMessage(session);

    	    // Create the email addresses involved
    	    try {
    	        InternetAddress from = new InternetAddress("donotreply@pod-server.com");
    	        message.setSubject(subject);
    	        message.setFrom(from);
    	        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(address));

    	        // Create a multi-part to combine the parts
    	        Multipart multipart = new MimeMultipart("alternative");

    	        // Create your text message part
    	        BodyPart messageBodyPart = new MimeBodyPart();
    	        messageBodyPart.setText("");
                //messageBodyPart.setHeader("Content-Type", "text/html; charset=UTF-8");
    	        // Add the text part to the multipart
    	        multipart.addBodyPart(messageBodyPart);

    	        // Create the html part
    	        messageBodyPart = new MimeBodyPart();
    	        //String htmlMessage = "Our html text";
                messageBodyPart.setHeader("Content-Type", "text/html; charset=UTF-8");
    	        messageBodyPart.setContent(htmlMessage, "text/html; charset=UTF-8");
                
    	        // Add html part to multi part
    	        multipart.addBodyPart(messageBodyPart);

    	        // Associate multi-part with message
    	        message.setContent(multipart);

    	        // Send message
    	        Transport transport = session.getTransport("smtp");
    	        transport.connect("mail.pod-server.com", "donotreply@pod-server.com", "podserver@321");
    	        System.out.println("Transport: "+transport.toString());
    	        transport.sendMessage(message, message.getAllRecipients());


    	    } catch (AddressException e) {
    	        // TODO Auto-generated catch block
    	        e.printStackTrace();
    	    } catch (MessagingException e) {
    	        // TODO Auto-generated catch block
    	        e.printStackTrace();
    	    }
     
    }

}
