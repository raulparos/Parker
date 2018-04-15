package com.parker.service.parkingspot.impl;

import com.parker.constants.ParkingSpotConstants;
import com.parker.dao.parkingspot.ParkingSpotDao;
import com.parker.data.ParkingSpotActiveIntervalData;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.User;
import com.parker.service.parkingspot.ParkingSpotService;
import com.parker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ParkingSpotServiceImpl implements ParkingSpotService {

    @Autowired
    private ParkingSpotDao parkingSpotDao;

    @Autowired
    private UserService userService;

    @Override
    public Long save(ParkingSpot parkingSpot, User user) {
        parkingSpot.setUser(user);
        userService.addParkingSpot(parkingSpot);
        userService.update(user);
        return parkingSpotDao.save(parkingSpot);
    }

    @Override
    public void update(ParkingSpot parkingSpot) {
        parkingSpotDao.update(parkingSpot);
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

    @Override
    public String formatActiveDaysIntervals(List<ParkingSpotActiveIntervalData> activeDaysIntervals) {
        StringBuilder builder = new StringBuilder();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        for (ParkingSpotActiveIntervalData activeDaysInterval : activeDaysIntervals) {
            builder.append(ParkingSpotConstants.PARKING_SPOT_ACTIVE_DAYS_INTERVALS_OUTER_DELIMITER_START);
            if (activeDaysInterval.getDayOfWeek() != null) {
                builder.append(activeDaysInterval.getDayOfWeek().name());
                builder.append(ParkingSpotConstants.PARKING_SPOT_ACTIVE_DAYS_INTERVALS_DAY_DELIMITER);
            }
            if (activeDaysInterval.getStartTime() != null) {
                builder.append(activeDaysInterval.getStartTime().format(dateTimeFormatter));
            }
            builder.append(ParkingSpotConstants.PARKING_SPOT_ACTIVE_DAYS_INTERVALS_INTERVAL_DELIMITER);
            if (activeDaysInterval.getEndTime() != null) {
                builder.append(activeDaysInterval.getEndTime().format(dateTimeFormatter));
            }
            builder.append(ParkingSpotConstants.PARKING_SPOT_ACTIVE_DAYS_INTERVALS_OUTER_DELIMITER_END);
        }

        return builder.toString();
    }

    @Override
    public List<ParkingSpotActiveIntervalData> formatActiveDaysIntervals(String activeDaysIntervals) {
        return null;
    }
}
