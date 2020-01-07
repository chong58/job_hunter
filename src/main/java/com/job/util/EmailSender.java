package com.job.util;


import com.job.service.UserService;
import com.job.service.impl.UserServiceImpl;
import com.mchange.v2.c3p0.util.TestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component
public class EmailSender extends Thread {

    @Autowired
    UserService userService;

    public  EmailSender emailSender;


    @PostConstruct
    public void init() {
        emailSender = this;
        emailSender.userService = this.userService;
    }


    public void run() {
        List<String> emailAddresses = new ArrayList<>();
        if(emailSender == null) {
            System.out.println("7777777");
        }
        if(emailSender.userService == null){
            System.out.println("99999999");
        }
        if(userService.getSubscribedEmail() != null) {
            emailAddresses = userService.getSubscribedEmail();
        }
        if(emailAddresses.size() > 0) {
            System.out.println("要发邮件了。。。");
            try {
                String to = "wangchong121@outlook.com";
                String subject = "subject";
                String msg = "email text....";
                final String from = "chongchongmy@yahoo.com";
                final String password = "ogehoseruwrofzoy";


                Properties props = new Properties();
                props.setProperty("mail.transport.protocol", "smtp");
                props.setProperty("mail.host", "smtp.mail.yahoo.com");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                props.put("mail.debug", "true");
                props.put("authentication", "plain");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "true");

                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.ssl.enable", "false");
                Session session = Session.getDefaultInstance(props,
                        new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(from, password);
                            }
                        });

                //session.setDebug(true);
                InternetAddress[] sendTo = new InternetAddress[emailAddresses.size()];
                for (int i = 0; i < emailAddresses.size(); i++) {
                    sendTo[i] = new InternetAddress(emailAddresses.get(i));
                }
                Transport transport = session.getTransport();
                InternetAddress addressFrom = new InternetAddress(from);

                MimeMessage message = new MimeMessage(session);
                message.setSender(addressFrom);
                message.setSubject(subject);
                message.setContent(msg, "text/plain");
                message.addRecipients(RecipientType.TO, sendTo);

                transport.connect();
                Transport.send(message);
                transport.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
