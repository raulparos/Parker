package com.parker.facade.parkingspot.impl;

import com.parker.data.ParkingSpotData;
import com.parker.domain.exception.user.UserException;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.User;
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
            User currentUser = userService.getCurrentUser();
            ParkingSpot parkingSpotFromData = new ParkingSpot();
            parkingSpotPopulator.populate(parkingSpotData, parkingSpotFromData);
            Long id = parkingSpotService.save(parkingSpotFromData, currentUser);
            parkingSpotData.setId(id);
        } catch (UserException e) {
            //todo: Log error
            parkingSpotData = null;
        }

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

    @Override
    public List<ParkingSpotData> findParkingSpotsInRadius(Float latitude, Float longitude, Integer radius) {
        List<ParkingSpot> parkingSpots = parkingSpotService.findParkingSpotsInRadius(latitude, longitude, radius);

        List<ParkingSpotData> parkingSpotsData = new ArrayList<>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            ParkingSpotData parkingSpotData = new ParkingSpotData();
            parkingSpotDataPopulator.populate(parkingSpot, parkingSpotData);

            parkingSpotsData.add(parkingSpotData);
        }

        return parkingSpotsData;
    }
}
