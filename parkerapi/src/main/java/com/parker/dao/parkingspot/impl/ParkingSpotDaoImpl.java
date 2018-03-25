package com.parker.dao.parkingspot.impl;

import com.parker.dao.parkingspot.ParkingSpotDao;
import com.parker.domain.model.ParkingSpot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ParkingSpotDaoImpl implements ParkingSpotDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ParkingSpot save(ParkingSpot parkingSpot) {
        Session session = sessionFactory.getCurrentSession();
        Long id = (Long) session.save(parkingSpot);
        return parkingSpot;
    }
}
