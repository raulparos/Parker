package com.parker.dao.user.impl;

import com.parker.dao.user.UserDao;
import com.parker.domain.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User find(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.
                createQuery("FROM User u WHERE u.email=:email AND u.password=:password").
                setParameter("email", email).
                setParameter("password", password).
                uniqueResult();

        return user;
    }
}
