package com.parker.facade.parkingspot;

import com.parker.data.parkingspot.ParkingSpotData;

import java.util.List;

public interface ParkingSpotFacade {
    ParkingSpotData addParkingSpot(ParkingSpotData parkingSpotData);

    List<ParkingSpotData> getParkingSpots(String parkingSpotIds);

    List<ParkingSpotData> findParkingSpotsInRadius(Float latitude, Float longitude, Integer radius);
}
