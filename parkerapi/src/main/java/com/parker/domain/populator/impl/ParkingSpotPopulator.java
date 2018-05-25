package com.parker.domain.populator.impl;

import com.parker.domain.populator.Populator;
import com.parker.data.parkingspot.ParkingSpotData;
import com.parker.domain.model.ParkingSpot;
import com.parker.service.parkingspot.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotPopulator implements Populator<ParkingSpotData, ParkingSpot> {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @Override
    public void populate(ParkingSpotData parkingSpotData, ParkingSpot parkingSpot) {
        parkingSpot.setLatitude(parkingSpotData.getLatitude());
        parkingSpot.setLongitude(parkingSpotData.getLongitude());
        parkingSpot.setAddress(parkingSpotData.getAddress());
        parkingSpot.setActiveDaysIntervals(parkingSpotService.formatActiveDaysIntervals(parkingSpotData.getActiveDaysIntervals()));
    }
}
