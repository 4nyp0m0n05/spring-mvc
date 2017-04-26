/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Userroles;
import model.Users;

/**
 *
 * @author Anypomonos
 */
public interface UserrolesDao {
    public void addUser(Userroles user);
    public List<Userroles> getAllUsers();
    public void updateUser(Userroles user);
    public void deleteUser(int id);
    public Userroles findById(int id);
    public Userroles findByUsername(String username);
    public List<Userroles> getUser(Users username);
}
