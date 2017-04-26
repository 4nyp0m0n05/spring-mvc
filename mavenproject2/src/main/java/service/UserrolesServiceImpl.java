/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.UserrolesDao;
import java.util.List;
import model.Userroles;
import model.Users;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anypomonos
 */
@Service
public class UserrolesServiceImpl implements UserrolesService{
    @Autowired
    private UserrolesDao userrolesDao;

    public void setUserrolesDao(UserrolesDao userrolesDao) {
        this.userrolesDao = userrolesDao;
    }

    @Override
    @Transactional
    public void addUser(Userroles user) {
        userrolesDao.addUser(user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<Userroles> getAllUsers() {
        return userrolesDao.getAllUsers(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void updateUser(Userroles user) {
        userrolesDao.updateUser(user);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userrolesDao.deleteUser(id);//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Userroles findById(int id){
        return userrolesDao.findById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<Userroles> getUser(Users username) {
        return userrolesDao.getUser(username); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public Userroles findByUsername(String username) {
        return userrolesDao.findByUsername(username);//To change body of generated methods, choose Tools | Templates.
    }

}
