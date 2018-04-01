package com.parker.facade.parkingspot.impl;

import com.parker.converter.Converter;
import com.parker.data.ParkingSpotData;
import com.parker.domain.exception.user.UserException;
import com.parker.domain.model.ParkingSpot;
import com.parker.facade.parkingspot.ParkingSpotFacade;
import com.parker.service.parkingspot.ParkingSpotService;
import com.parker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotFacadeImpl implements ParkingSpotFacade {

    @Autowired
    private Converter<ParkingSpotData, ParkingSpot> parkingSpotConverter;

    @Autowired
    private Converter<ParkingSpot, ParkingSpotData> parkingSpotDataConverter;

    @Autowired
    private ParkingSpotService parkingSpotService;

    @Autowired
    private UserService userService;

    @Override
    public ParkingSpotData addParkingSpot(ParkingSpotData parkingSpotData) {
        try {
            userService.getCurrentUser();
        } catch (UserException e) {
            e.printStackTrace();
        }
        ParkingSpot parkingSpotFromData = new ParkingSpot();
        parkingSpotConverter.convert(parkingSpotData, parkingSpotFromData);

        Long id = parkingSpotService.addParkingSpot(parkingSpotFromData);
        parkingSpotData.setId(id);

        return parkingSpotData;
    }
}
