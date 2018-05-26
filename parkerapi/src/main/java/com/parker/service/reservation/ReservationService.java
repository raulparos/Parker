package com.parker.service.reservation;

import com.parker.domain.exception.reservation.ReservationException;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    Long save(Reservation reservation) throws ReservationException;

    void delete(Long reservationId);

    List<Reservation> getReservationsForDate(List<Reservation> reservations, Date date);

    List<Reservation> sortReservationsByStartTime(List<Reservation> listToSort);

    boolean validateReservationInterval(Reservation reservation, ParkingSpot parkingSpot);
}
