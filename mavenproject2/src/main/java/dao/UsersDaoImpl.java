/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Travel;
import model.Userroles;
import model.Users;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anypomonos
 */
@Repository
public class UsersDaoImpl implements UsersDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void addUser(Users user) {
        this.sessionFactory.getCurrentSession().save(user); //To change body of generated methods, choose Tools | Templates.
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Users> getAllUsers() {
        return this.sessionFactory.getCurrentSession().createQuery("from Users").list(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateUser(Users user) {
        this.sessionFactory.getCurrentSession().update(user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(String username) {
        Users user=(Users) sessionFactory.getCurrentSession().load(Users.class, username);
        if(user!=null){
            this.sessionFactory.getCurrentSession().delete(user);
        }
    }

    @Override
    public boolean checkLogin(String userName, String userPassword) {
        Session session=sessionFactory.openSession();
        boolean userFound=false;
        String query="from Users as o where o.username=? and o.password=?";
        Query query_hib=session.createQuery(query);
        query_hib.setParameter(0, userName);
        query_hib.setParameter(1, userPassword);
        List list=query_hib.list();
        if((list!=null)&&(list.size()>0)){
            userFound=true;
        }
        session.close();
        return userFound;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Users findByUsername(String username) {
        List<Users> users=new ArrayList<Users>();
        users=getSessionFactory().getCurrentSession().createQuery("from Users where username=?")
                .setParameter(0, username).list();
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

   
    
}
