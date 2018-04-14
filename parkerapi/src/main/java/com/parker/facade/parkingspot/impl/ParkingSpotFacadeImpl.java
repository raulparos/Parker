package com.parker.facade.parkingspot.impl;

import com.parker.data.ParkingSpotData;
import com.parker.domain.exception.user.UserException;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.populator.Populator;
import com.parker.facade.parkingspot.ParkingSpotFacade;
import com.parker.service.parkingspot.ParkingSpotService;
import com.parker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ParkingSpotFacadeImpl implements ParkingSpotFacade {

    @Autowired
    private Populator<ParkingSpotData, ParkingSpot> parkingSpotPopulator;

    @Autowired
    private Populator<ParkingSpot, ParkingSpotData> parkingSpotDataPopulator;

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
        parkingSpotPopulator.populate(parkingSpotData, parkingSpotFromData);

        Long id = parkingSpotService.addParkingSpot(parkingSpotFromData);
        parkingSpotData.setId(id);

        return parkingSpotData;
    }

    @Override
    public List<ParkingSpotData> getParkingSpots(String parkingSpotIds) {
        List<ParkingSpot> parkingSpots = parkingSpotService.find(parkingSpotIds);

        List<ParkingSpotData> parkingSpotsData = new ArrayList<>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            ParkingSpotData parkingSpotData = new ParkingSpotData();
            parkingSpotDataPopulator.populate(parkingSpot, parkingSpotData);

            parkingSpotsData.add(parkingSpotData);
        }

        return parkingSpotsData;
    }
}
