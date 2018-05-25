package com.parker.service.parkingspot;

import com.parker.data.parkingspot.ParkingSpotActiveIntervalData;
import com.parker.data.parkingspot.ParkingSpotFreeIntervalData;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.Reservation;
import com.parker.domain.model.User;

import java.util.Date;
import java.util.List;

public interface ParkingSpotService {
    Long save(ParkingSpot parkingSpot, User user);

    void update(ParkingSpot parkingSpot);

    ParkingSpot find(Long id);

    List<ParkingSpot> find(String ids);

    List<ParkingSpot> find(List<Long> ids);

    void addReservation(ParkingSpot parkingSpot, Reservation reservation);

    String formatActiveDaysIntervals(List<ParkingSpotActiveIntervalData> activeDaysIntervals);

    List<ParkingSpotActiveIntervalData> formatActiveDaysIntervals(String activeDaysIntervals);

    List<ParkingSpot> findParkingSpotsInRadius(Float latitude, Float longitude, Integer radius);

    List<ParkingSpotFreeIntervalData> getFreeIntervalsForParkingSpotId(Long parkingSpotId, Date date);
}
