package com.parker.dao.parkingspot;

import com.parker.domain.model.ParkingSpot;

import java.util.List;

public interface ParkingSpotDao {
    Long save(ParkingSpot parkingSpot);

    void update(ParkingSpot parkingSpot);

    ParkingSpot find(Long id);

    List<ParkingSpot> find(List<Long> ids);

    List<ParkingSpot> findAll();
}
