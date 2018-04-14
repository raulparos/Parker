package com.parker.service.parkingspot.impl;

import com.parker.constants.ParkingSpotConstants;
import com.parker.dao.parkingspot.ParkingSpotDao;
import com.parker.domain.model.ParkingSpot;
import com.parker.service.parkingspot.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParkingSpotServiceImpl implements ParkingSpotService {

    @Autowired
    private ParkingSpotDao parkingSpotDao;

    @Override
    public Long addParkingSpot(ParkingSpot parkingSpot) {
        return parkingSpotDao.save(parkingSpot);
    }

    @Override
    public ParkingSpot find(Long id) {
        return parkingSpotDao.find(id);
    }

    @Override
    public List<ParkingSpot> find(String ids) {
        String[] idsSplit = StringUtils.split(ids, ParkingSpotConstants.PARKING_SPOT_IDS_DELIMITER);

        List<Long> idsList = new ArrayList<>();
        for (int index = 0; index < idsSplit.length; index++) {
            try {
                Long id = Long.parseLong(idsSplit[0]);
                idsList.add(id);
            }
            catch (NumberFormatException e) {
                //todo: Log the error
            }
        }

        if (idsList.size() > 0) {
            return find(idsList);
        }

        return new ArrayList<>();
    }

    @Override
    public List<ParkingSpot> find(List<Long> ids) {
        return parkingSpotDao.find(ids);
    }
}
