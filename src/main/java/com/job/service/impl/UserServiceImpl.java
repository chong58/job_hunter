package com.job.service.impl;

import com.job.dao.JobDao;
import com.job.dao.UserDao;
import com.job.model.User;
import com.job.service.RedisService;
import com.job.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;


    public void addUser(User user){
        userDao.addUser(user);
    }

    public void deleteUser(int id){
        userDao.deleteUser(id);
    }

    public void updateUser(User user){
        userDao.updateUser(user);
    }

    public User getUser(int id){
        return userDao.getUser(id);
    }

    public User getUserByEmail(String email){
        return userDao.getUserByEmail(email);
    }

    public void startEmailService(int id){
        userDao.startEmailService(id);
    }

    public void stopEmailService(int id){
        userDao.stopEmailService(id);
    }

    public List<String> getSubscribedEmail(){
        List<String> res = new ArrayList<>();
        if(userDao == null) System.out.println(9999);
        if(userDao.getSubscribedEmail() == null){
            return res;
        }
        System.out.println(res.size());
        res = userDao.getSubscribedEmail();
        return res;
    }

}
