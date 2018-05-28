package com.parker.domain.populator.impl;

import com.parker.data.reservation.ReservationData;
import com.parker.domain.model.Reservation;
import com.parker.domain.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class ReservationDataPopulator implements Populator<Reservation, ReservationData> {
    @Override
    public void populate(Reservation reservation, ReservationData reservationData) {
        reservationData.setId(reservation.getId());
        reservationData.setParkingSpotId(reservation.getParkingSpot().getId());
        reservationData.setDate(reservation.getDate());
        reservationData.setStartTime(reservation.getStartTime());
        reservationData.setEndTime(reservation.getEndTime());
    }
}
