package com.parker.converter.populator.impl;

import com.parker.converter.populator.Populator;
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
