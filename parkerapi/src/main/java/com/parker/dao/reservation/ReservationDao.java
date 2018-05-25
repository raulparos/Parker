package com.parker.dao.reservation;

import com.parker.domain.model.Reservation;

public interface ReservationDao {
    Long save(Reservation reservation);
}
