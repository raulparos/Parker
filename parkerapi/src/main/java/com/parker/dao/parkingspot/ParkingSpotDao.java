package com.parker.dao.parkingspot;

import com.parker.domain.model.ParkingSpot;

public interface ParkingSpotDao {
    Long save(ParkingSpot parkingSpot);
    ParkingSpot find(Long id);
}
