package com.parker.service.parkingspot;

import com.parker.domain.model.ParkingSpot;

import java.util.List;

public interface ParkingSpotService {
    Long addParkingSpot(ParkingSpot parkingSpot);

    ParkingSpot find(Long id);

    List<ParkingSpot> find(String ids);

    List<ParkingSpot> find(List<Long> ids);
}
