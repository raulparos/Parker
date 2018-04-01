package com.parker.converter.populator.impl;

import com.parker.converter.populator.Populator;
import com.parker.data.ParkingSpotData;
import com.parker.domain.model.ParkingSpot;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotDataPopulator implements Populator<ParkingSpot, ParkingSpotData> {
    @Override
    public void populate(ParkingSpot parkingSpot, ParkingSpotData parkingSpotData) {
        parkingSpotData.setId(parkingSpot.getId());
        parkingSpotData.setUserId(parkingSpot.getUser().getId());
        parkingSpotData.setLatitude(parkingSpot.getLatitude());
        parkingSpotData.setLongitude(parkingSpot.getLongitude());
    }
}
