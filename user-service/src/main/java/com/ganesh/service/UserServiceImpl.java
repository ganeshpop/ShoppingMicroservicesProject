package com.ganesh.service;

import com.ganesh.bean.User;
import com.ganesh.persistence.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Collection<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User insertUser(User user) {
        return userDao.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getById(id);
    }

    @Override
    public User getUserByName(String userName) {
        return userDao.getUserByName(userName);
    }

    @Override
    public User deleteUserById(int id) {
        User user = userDao.getById(id);
        userDao.deleteById(id);
        return user;
    }

    @Override
    public User updateUserName(int id, String name) {
        int rows = userDao.updateUserName(id, name);
        if (rows > 0) return userDao.getById(id);
        return null;
    }

    @Override
    public User updateUserPassword(int id, String password) {
        int rows = userDao.updateUserPassword(id, password);
        if (rows > 0) return userDao.getById(id);
        return null;
    }
}



