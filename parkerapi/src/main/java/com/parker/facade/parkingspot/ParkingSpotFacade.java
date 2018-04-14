package com.parker.facade.parkingspot;

import com.parker.data.ParkingSpotData;

import java.util.List;

public interface ParkingSpotFacade {
    ParkingSpotData addParkingSpot(ParkingSpotData parkingSpotData);

    List<ParkingSpotData> getParkingSpots(String parkingSpotIds);
}
