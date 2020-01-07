package com.job.service;

import com.job.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void addUser(User user);
    void deleteUser(int id);
    void updateUser(User user);
    User getUser(int id);
    User getUserByEmail(String email);
    void startEmailService(int id);
    void stopEmailService(int id);
    List<String> getSubscribedEmail();


}
