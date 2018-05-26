package com.parker.facade.parkingspot;

import com.parker.data.parkingspot.FilterData;
import com.parker.data.parkingspot.ParkingSpotData;
import com.parker.data.parkingspot.ParkingSpotFreeIntervalData;

import java.text.ParseException;
import java.util.List;

public interface ParkingSpotFacade {
    ParkingSpotData addParkingSpot(ParkingSpotData parkingSpotData);

    List<ParkingSpotData> getParkingSpots(String parkingSpotIds);

    List<ParkingSpotData> findParkingSpotsInRadius(Float latitude, Float longitude, Integer radius);

    List<ParkingSpotData> findFilteredParkingSpotsInRadius(FilterData filterData);

    List<ParkingSpotFreeIntervalData> getFreeIntervals(String parkingSpotId, String date) throws ParseException;
}
