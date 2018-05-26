package com.parker.dao.parkingspot.impl;

import com.parker.dao.parkingspot.ParkingSpotDao;
import com.parker.domain.model.ParkingSpot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ParkingSpotDaoImpl implements ParkingSpotDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long save(ParkingSpot parkingSpot) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(parkingSpot);
    }

    @Override
    public void update(ParkingSpot parkingSpot) {
        Session session = sessionFactory.getCurrentSession();
        session.update(parkingSpot);
    }

    @Override
    public ParkingSpot find(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (ParkingSpot) session.createQuery("FROM ParkingSpot p WHERE p.id=:id").
                setParameter("id", id).uniqueResult();
    }

    @Override
    public List<ParkingSpot> find(List<Long> ids) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM ParkingSpot p WHERE p.id IN (:ids)").
                setParameterList("ids", ids).list();
    }

    @Override
    public List<ParkingSpot> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM ParkingSpot").list();
    }
}
