/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Travel;
import model.Users;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anypomonos
 */
@Repository
public class TravelDaoImpl implements TravelDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void addTravel(Travel travel) {
        this.sessionFactory.getCurrentSession().save(travel);
        
       /* Query query= this.sessionFactory.getCurrentSession().createQuery("INSERT INTO `travel` (`travelid`, `username`, `place`, `goal`, `startdate`, `finishdate`, `cost`, `information`) VALUES(:id,:us,:pl,:go,:sd,:fd,:co,:in)");
        query.setParameter("id", travel.getTravelid());
        query.setParameter("us", travel.getUsers());
        query.setParameter("pl", travel.getPlace());
        query.setParameter("go", travel.getGoal());
        query.setParameter("sd", travel.getStartdate());
        query.setParameter("fd", travel.getFinishdate());
        query.setParameter("co", travel.getCost());
        query.setParameter("in", travel.getInformation());
        query.executeUpdate();*/
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Travel> getTravels() {
        return this.sessionFactory.getCurrentSession().createQuery("from Travel").list();  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTravel(Travel travel) {
        this.sessionFactory.getCurrentSession().update(travel); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTravel(int id) {
        Travel travel=(Travel) sessionFactory.getCurrentSession().load(Travel.class, id);
        if(travel!=null){
            this.sessionFactory.getCurrentSession().delete(travel);
        }
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Travel> getTravels(Date date1,Date date2) {
        return this.sessionFactory.getCurrentSession().createQuery("from Travel where startdate>? and finishdate<? order by startdate").setParameter(0, date1).setParameter(1,date2).list();//To change body of generated methods, choose Tools | Templates.
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<Travel> getTravels(Date date) {
        return this.sessionFactory.getCurrentSession().createQuery("from Travel where startdate>? order by startdate").setParameter(0, date).list();//To change body of generated methods, choose Tools | Templates.
    }
    @Override
    @SuppressWarnings("unchecked")
    public Travel findById(int id) {
        List<Travel> users=new ArrayList<Travel>();
        users=getSessionFactory().getCurrentSession().createQuery("from Travel where id=?")
                .setParameter(0, id).list();
        if(users.size()>0){
            return users.get(0);
        }else{
            return null;
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Travel> getTravel(String username) {
        return this.sessionFactory.getCurrentSession().createQuery("from Travel where users.username=?").setParameter(0, username).list();//To change body of generated methods, choose Tools | Templates.
    }
   
}
