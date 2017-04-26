/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Users;

/**
 *
 * @author Anypomonos
 */
public interface UsersDao {
    public void addUser(Users user);
    public List<Users> getAllUsers();
    public void updateUser(Users user);
    public void deleteUser(String username);
    public boolean checkLogin(String userName, String userPassword);
    public Users findByUsername(String username);
    
}
