package com.ganesh.service;

import com.ganesh.bean.User;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface UserService {

    Collection<User> getAllUsers();
    User insertUser(User user);
    User getUserById(int id);
    User getUserByName(String userName);
    User deleteUserById(int id);
    User updateUserName(int id, String name);
    User updateUserPassword(int id, String password);
}