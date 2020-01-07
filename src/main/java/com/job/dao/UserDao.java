package com.job.dao;

import com.job.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUser(int id);
    User getUserByEmail(String email);
    void startEmailService(int id);
    void stopEmailService(int id);
    List<String> getSubscribedEmail();
}
