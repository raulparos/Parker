package com.parker.facade.reservation;

import com.parker.data.reservation.ReservationData;
import com.parker.domain.exception.reservation.ReservationException;

public interface ReservationFacade {
    ReservationData addReservation(ReservationData reservationData) throws ReservationException;
}
