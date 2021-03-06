package com.parker.service.user.impl;

import com.parker.dao.user.UserDao;
import com.parker.domain.exception.user.UserException;
import com.parker.domain.exception.user.UserNotFoundException;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.Reservation;
import com.parker.domain.model.User;
import com.parker.service.user.UserService;
import com.parker.util.authentication.UserUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Locale getCurrentLocale() {
        return Locale.ENGLISH;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return userDao.find((String) authentication.getPrincipal());
        return userDao.find("raul.paros@gmail.com");
    }

    @Override
    public User authenticateUser(String email, String password) throws UserException {
        User user = userDao.find(email, UserUtils.encryptPassword(password));
        if (user == null) {
            throw new UserNotFoundException("The email/password combination does not exist");
        }

        return user;
    }

    @Override
    public void addParkingSpotToCurrentUser(ParkingSpot parkingSpot) {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            List<ParkingSpot> parkingSpots = currentUser.getParkingSpots();
            parkingSpots.add(parkingSpot);
            currentUser.setParkingSpots(parkingSpots);
        }
    }

    @Override
    public void addReservationToCurrentUser(Reservation reservation) {
        User currentUser = getCurrentUser();
        if (currentUser != null) {
            List<Reservation> reservations = currentUser.getReservations();
            reservations.add(reservation);
            currentUser.setReservations(reservations);
        }
    }
}
