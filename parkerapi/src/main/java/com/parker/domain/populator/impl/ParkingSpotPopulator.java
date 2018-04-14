package com.parker.domain.populator.impl;

import com.parker.domain.populator.Populator;
import com.parker.data.ParkingSpotData;
import com.parker.domain.model.ParkingSpot;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotPopulator implements Populator<ParkingSpotData, ParkingSpot> {
    @Override
    public void populate(ParkingSpotData parkingSpotData, ParkingSpot parkingSpot) {
        parkingSpot.setLatitude(parkingSpotData.getLatitude());
        parkingSpot.setLongitude(parkingSpotData.getLongitude());
    }
}
