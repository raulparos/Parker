package com.parker.facade.reservation;

import com.parker.data.reservation.ReservationData;
import com.parker.domain.exception.reservation.ReservationException;

import java.util.List;

public interface ReservationFacade {
    ReservationData addReservation(ReservationData reservationData) throws ReservationException;

    void deleteReservation(String reservationId);

    List<ReservationData> findReservationsForUser();
}
