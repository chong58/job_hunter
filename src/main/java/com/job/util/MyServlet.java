package com.job.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @Autowired
    private EmailSender emailController;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    if(emailController == null){
        System.out.println(9999999);
    }

    if(emailController != null) {
        emailController.start(); // servlet 上下文初始化时启动 socket

    }

    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)

            throws ServletException, IOException {

    }

    public void destory(){

        if (emailController != null && emailController.isInterrupted()) {

            emailController.interrupt();

        }

    }
}
