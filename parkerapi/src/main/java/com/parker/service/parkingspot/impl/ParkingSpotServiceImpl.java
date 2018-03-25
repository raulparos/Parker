package com.parker.service.parkingspot.impl;

import com.parker.dao.parkingspot.ParkingSpotDao;
import com.parker.domain.model.ParkingSpot;
import com.parker.service.parkingspot.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ParkingSpotServiceImpl implements ParkingSpotService {

    @Autowired
    private ParkingSpotDao parkingSpotDao;

    @Override
    public ParkingSpot addParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotDao.save(parkingSpot);
    }
}
