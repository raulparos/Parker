package com.parker.domain.populator.impl;

import com.parker.domain.populator.Populator;
import com.parker.data.ParkingSpotData;
import com.parker.domain.model.ParkingSpot;
import com.parker.service.parkingspot.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotDataPopulator implements Populator<ParkingSpot, ParkingSpotData> {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @Override
    public void populate(ParkingSpot parkingSpot, ParkingSpotData parkingSpotData) {
        parkingSpotData.setId(parkingSpot.getId());
        parkingSpotData.setUserId(parkingSpot.getUser().getId());
        parkingSpotData.setLatitude(parkingSpot.getLatitude());
        parkingSpotData.setLongitude(parkingSpot.getLongitude());
        parkingSpotData.setAddress(parkingSpot.getAddress());
        parkingSpotData.setActiveDaysIntervals(parkingSpotService.formatActiveDaysIntervals(parkingSpot.getActiveDaysIntervals()));
    }
}
