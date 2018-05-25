package com.parker.dao.reservation.impl;

import com.parker.dao.reservation.ReservationDao;
import com.parker.domain.model.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long save(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(reservation);
    }
}