package com.job.util;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class EmailValidator {

    String emailAddress = null;
    String authentiCationCode =null;
    public void setEmail(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public void setCode(String authentiCationCode){
        this.authentiCationCode = authentiCationCode;
    }

    public void run() {


            System.out.println("要发邮件了。。。");
            try {
                String to = "wangchong121@outlook.com";
                String subject = "subject";
                String msg = authentiCationCode;
                final String from = "chongwang2018@gmail.com";
                final String password = "q345weft..380";


                Properties props = new Properties();
                props.setProperty("mail.transport.protocol", "smtp");
                props.setProperty("mail.host", "smtp.gmail.com");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                props.put("mail.debug", "true");
                props.put("authentication", "plain");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "true");
                Session session = Session.getDefaultInstance(props,
                        new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(from, password);
                            }
                        });

                //session.setDebug(true);
                InternetAddress sendTo = new InternetAddress(emailAddress);

                Transport transport = session.getTransport();
                InternetAddress addressFrom = new InternetAddress(from);

                MimeMessage message = new MimeMessage(session);
                message.setSender(addressFrom);
                message.setSubject(subject);
                message.setContent(msg, "text/plain");
                message.addRecipient(Message.RecipientType.TO, sendTo);

                transport.connect();
                Transport.send(message);
                transport.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

    }


}
