package com.job.controller;

import com.job.model.User;
import com.job.service.JobService;
import com.job.service.RedisService;
import com.job.service.UserService;
import com.job.util.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    RedisService redisService;

//    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE = "hello";
//    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS = (long) 120;


    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        if(email == null) {
            System.out.println("9999999");
            return null;
        }
        String password = request.getParameter("password");
        User user = userService.getUserByEmail(email);
        String realPassword = user.getPassword();
        if(password.equals(realPassword)){
            int id = user.getId();
            request.getSession().setAttribute("userId",id);
            return "redirect:listJob";
        }
        return "login";
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");

        request.getSession().setAttribute("email",email);
        request.getSession().setAttribute("password",password);
        request.getSession().setAttribute("name",name);

        String authenticationCode = getRandomCode();
        emailValidator.setEmail(email);
        emailValidator.setCode(authenticationCode);
        emailValidator.run();

        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + email,authenticationCode);
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + email, AUTH_CODE_EXPIRE_SECONDS);

        return "validateCode";
    }

    @RequestMapping("/validateCode")
    public String validateCode(HttpServletRequest request, HttpServletResponse response){
        String code = request.getParameter("authenticationCode");
        String email = request.getSession().getAttribute("email").toString();
        String realCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + email);
        if(realCode.equals(code)){
            User user = new User();
            String name = request.getSession().getAttribute("name").toString();
            String password = request.getSession().getAttribute("password").toString();
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setStartEmail(0);

            userService.addUser(user);
            return "success";
        }
        return "error";
    }

    public String getRandomCode(){
        StringBuffer sb = new StringBuffer();
        Random rand = new Random();
        for(int i=0;i < 6;i++){
            int num = rand.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }

}
