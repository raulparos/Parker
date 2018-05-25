package com.parker.facade.reservation.impl;

import com.parker.data.reservation.ReservationData;
import com.parker.domain.exception.parkingspot.InvalidIntervalException;
import com.parker.domain.exception.reservation.ReservationException;
import com.parker.domain.exception.user.UserException;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.Reservation;
import com.parker.domain.model.User;
import com.parker.domain.populator.Populator;
import com.parker.facade.reservation.ReservationFacade;
import com.parker.service.parkingspot.ParkingSpotService;
import com.parker.service.reservation.ReservationService;
import com.parker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReservationFacadeImpl implements ReservationFacade {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @Autowired
    private Populator<ReservationData, Reservation> reservationPopulator;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Override
    public ReservationData addReservation(ReservationData reservationData) throws ReservationException {
        try {
            User currentUser = userService.getCurrentUser();
            Reservation reservation = new Reservation();
            reservationPopulator.populate(reservationData, reservation);
            reservation.setUser(currentUser);
            ParkingSpot parkingSpot = parkingSpotService.find(reservationData.getParkingSpotId());

            if (parkingSpot != null) {
                reservation.setParkingSpot(parkingSpot);
                reservationService.save(reservation);
            }
            else {
                throw new ReservationException("No parkinng spot with id: " + reservationData.getParkingSpotId() + " found");
            }
        } catch (UserException e) {
            //todo: log error
            reservationData = null;
        }

        return reservationData;
    }
}