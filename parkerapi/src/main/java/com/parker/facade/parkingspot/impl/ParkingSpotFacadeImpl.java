package com.parker.facade.parkingspot.impl;

import com.parker.converter.Converter;
import com.parker.data.ParkingSpotData;
import com.parker.domain.model.ParkingSpot;
import com.parker.facade.parkingspot.ParkingSpotFacade;
import com.parker.service.parkingspot.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotFacadeImpl implements ParkingSpotFacade {

    @Autowired
    private Converter<ParkingSpotData, ParkingSpot> parkingSpotConverter;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Override
    public ParkingSpotData addParkingSpot(ParkingSpotData parkingSpotData) {
        return null;
    }
}
