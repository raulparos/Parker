package com.parker.dao.reservation;

import com.parker.domain.model.Reservation;
import com.parker.domain.model.User;

import java.util.List;

public interface ReservationDao {
    Long save(Reservation reservation);

    Reservation find(Long id);

    void delete(Reservation reservation);

    List<Reservation> findForUser(User user);
}
