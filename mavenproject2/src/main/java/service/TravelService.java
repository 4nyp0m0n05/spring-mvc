/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Date;
import java.util.List;
import model.Travel;
import model.Users;

/**
 *
 * @author Anypomonos
 */

public interface TravelService {
    public void addTravel(Travel travel);
    public List<Travel> getTravels();
    public void updateTravel(Travel travel);
    public void deleteTravel(int id);
    public Travel findById(int id);
    public List<Travel> getTravel(String username);
    /**
     *
     * @param date
     * @return
     */
    public List<Travel> getTravels(Date date1,Date date2);
    public List<Travel> getTravels(Date date);
}
