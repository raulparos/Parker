package com.parker.service.reservation.impl;

import com.parker.dao.reservation.ReservationDao;
import com.parker.data.parkingspot.ParkingSpotFreeIntervalData;
import com.parker.data.reservation.ReservationData;
import com.parker.domain.exception.parkingspot.InvalidIntervalException;
import com.parker.domain.exception.reservation.ReservationException;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.Reservation;
import com.parker.domain.model.User;
import com.parker.service.parkingspot.ParkingSpotService;
import com.parker.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Override
    public Long save(Reservation reservation) throws ReservationException {
        if (validateReservationInterval(reservation)) {
            parkingSpotService.addReservation(reservation.getParkingSpot(), reservation);

            return reservationDao.save(reservation);
        }
        else {
            throw new ReservationException("Reservation interval does not fit in parking spot schedule");
        }
    }

    @Override
    public List<Reservation> getReservationsForDate(List<Reservation> reservations, Date date) {
        List<Reservation> filteredReservations = new ArrayList<>();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date);
        for (Reservation reservation : reservations) {
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date);
            if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
                filteredReservations.add(reservation);
            }
        }

        return filteredReservations;
    }

    @Override
    public List<Reservation> sortReservationsByStartTime(List<Reservation> listToSort) {
        Reservation reservation = null;
        List<Reservation> sortedList = new ArrayList<>();
        int index = 0;
        while (!listToSort.isEmpty()) {
            if (index == 0) {
                reservation = listToSort.get(index);
            } else if (reservation.getStartTime().compareTo(listToSort.get(index).getStartTime()) > 0) {
                reservation = listToSort.get(index);
            }

            index++;
            if (index == listToSort.size()) {
                sortedList.add(reservation);
                listToSort.remove(reservation);
                index = 0;
            }
        }
        return sortedList;
    }

    private boolean validateReservationInterval(Reservation reservation) {
        List<ParkingSpotFreeIntervalData> freeIntervals = parkingSpotService.getFreeIntervalsForParkingSpotId(reservation.getParkingSpot().getId(),
                reservation.getDate());

        for (ParkingSpotFreeIntervalData freeInterval : freeIntervals) {
            if (reservation.getStartTime().compareTo(freeInterval.getStartTime()) >= 0
                    && reservation.getEndTime().compareTo(freeInterval.getEndTime()) <= 0) {
                return true;
            }
        }

        return false;
    }
}
