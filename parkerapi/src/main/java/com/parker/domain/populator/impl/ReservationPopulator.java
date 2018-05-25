package com.parker.domain.populator.impl;

import com.parker.data.reservation.ReservationData;
import com.parker.domain.model.Reservation;
import com.parker.domain.populator.Populator;
import org.springframework.stereotype.Component;

@Component
public class ReservationPopulator implements Populator<ReservationData, Reservation> {
    @Override
    public void populate(ReservationData reservationData, Reservation reservation) {
        reservation.setDate(reservationData.getDate());
        reservation.setStartTime(reservationData.getStartTime());
        reservation.setEndTime(reservationData.getEndTime());
    }
}
