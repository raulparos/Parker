package com.parker.service.parkingspot;

import com.parker.data.ParkingSpotActiveIntervalData;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.User;

import java.util.List;

public interface ParkingSpotService {
    Long save(ParkingSpot parkingSpot, User user);

    ParkingSpot find(Long id);

    List<ParkingSpot> find(String ids);

    List<ParkingSpot> find(List<Long> ids);

    String formatActiveDaysIntervals(List<ParkingSpotActiveIntervalData> activeDaysIntervals);

    List<ParkingSpotActiveIntervalData> formatActiveDaysIntervals(String activeDaysIntervals);
}
