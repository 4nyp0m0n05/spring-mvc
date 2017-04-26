/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.TravelDao;
import java.util.Date;
import java.util.List;
import model.Travel;
import model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Anypomonos
 */
@Service
public class TravelServiceImpl implements TravelService{
    @Autowired
    private TravelDao travelDao;
    
    public void setTravelDao(TravelDao travelDao) {
        this.travelDao = travelDao;
    }

    @Override
    @Transactional
    public void addTravel(Travel travel) {
        travelDao.addTravel(travel); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<Travel> getTravels() {
        return  travelDao.getTravels();//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void updateTravel(Travel travel) {
        travelDao.updateTravel(travel); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void deleteTravel(int id) {
        travelDao.deleteTravel(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<Travel> getTravels(Date date1,Date date2) {
        return travelDao.getTravels(date1,date2);//To change body of generated methods, choose Tools | Templates.
    }
    @Override
    
    public List<Travel> getTravels(Date date) {
        return travelDao.getTravels(date);//To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    @Transactional
    public Travel findById(int id) {
        return travelDao.findById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<Travel> getTravel(String username) {
        return travelDao.getTravel(username); //To change body of generated methods, choose Tools | Templates.
    }
}
