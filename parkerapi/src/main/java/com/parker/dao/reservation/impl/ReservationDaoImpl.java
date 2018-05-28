package com.parker.dao.reservation.impl;

import com.parker.dao.reservation.ReservationDao;
import com.parker.domain.model.Reservation;
import com.parker.domain.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long save(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(reservation);
    }

    @Override
    public Reservation find(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Reservation) session.createQuery("FROM Reservation r WHERE r.id=:id").
                setParameter("id", id).uniqueResult();
    }

    @Override
    public void delete(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(reservation);
    }

    @Override
    public List<Reservation> findForUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("FROM Reservation r WHERE r.user IN (:user)").
                setParameter("user", user).list();
    }
}
