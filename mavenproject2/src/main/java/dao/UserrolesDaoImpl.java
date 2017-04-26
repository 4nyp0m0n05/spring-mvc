/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Userroles;
import model.Users;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anypomonos
 */
@Repository
public class UserrolesDaoImpl implements UserrolesDao{
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(Userroles user) {
        this.sessionFactory.getCurrentSession().save(user); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Userroles> getAllUsers() {
        return this.sessionFactory.getCurrentSession().createQuery("from Userroles").list(); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void updateUser(Userroles user) {
        this.sessionFactory.getCurrentSession().update(user); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteUser(int id) {
        Userroles user=(Userroles) sessionFactory.getCurrentSession().load(Userroles.class, id);
        if(user!=null){
            this.sessionFactory.getCurrentSession().delete(user);
        } //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @SuppressWarnings("unchecked")
    public Userroles findById(int id) {
        List<Userroles> users=new ArrayList<Userroles>();
        users=getSessionFactory().getCurrentSession().createQuery("from Userroles where id=?")
                .setParameter(0, id).list();
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }
    @Override
    @SuppressWarnings("unchecked")
    public List<Userroles> getUser(Users username) {
        return this.sessionFactory.getCurrentSession().createQuery("from Userroles where users=?").setParameter(0, username).list();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @SuppressWarnings("unchecked")
    public Userroles findByUsername(String username) {
        List<Userroles> users=new ArrayList<Userroles>();
        users=getSessionFactory().getCurrentSession().createQuery("from Userroles where username=?")
                .setParameter(0, username).list();
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }
}
