/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UsersDao;
import java.util.List;

import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anypomonos
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UsersDao userDao;

    public void setUserDao(UsersDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(Users user) {
        userDao.addUser(user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<Users> getAllUsers() {
        return userDao.getAllUsers();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void updateUser(Users user) {
        userDao.updateUser(user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        userDao.deleteUser(username); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public boolean checkLogin(String userName, String userPassword) {
        return userDao.checkLogin(userName, userPassword);//To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    @Transactional
    public Users findByUsername(String username) {
        return userDao.findByUsername(username); //To change body of generated methods, choose Tools | Templates.
    }
    
   
}
