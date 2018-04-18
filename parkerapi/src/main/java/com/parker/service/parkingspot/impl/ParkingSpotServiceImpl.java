package com.parker.service.parkingspot.impl;

import com.parker.constants.ParkingSpotConstants;
import com.parker.dao.parkingspot.ParkingSpotDao;
import com.parker.data.ParkingSpotActiveIntervalData;
import com.parker.domain.model.ParkingSpot;
import com.parker.domain.model.User;
import com.parker.service.parkingspot.ParkingSpotService;
import com.parker.service.user.UserService;
import com.parker.util.GeolocationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalTime;
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
        userService.addParkingSpotToCurrentUser(parkingSpot);
        return parkingSpotDao.save(parkingSpot);
    }

    @Override
    public ParkingSpot find(Long id) {
        return parkingSpotDao.find(id);
    }

    @Override
    public List<ParkingSpot> find(String ids) {
        String[] idsSplit = ids.split(ParkingSpotConstants.PARKING_SPOT_IDS_DELIMITER);

        List<Long> idsList = new ArrayList<>();
        for (int index = 0; index < idsSplit.length; index++) {
            try {
                Long id = Long.parseLong(idsSplit[index]);
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
        List<ParkingSpot> parkingSpots = parkingSpotDao.find(ids);
        if (parkingSpots.size() > ids.size()) {
            //todo: Log db error with data inconsistency
        }
        return parkingSpots;
    }

    @Override
    public String formatActiveDaysIntervals(List<ParkingSpotActiveIntervalData> activeDaysIntervals) {
        if (CollectionUtils.isEmpty(activeDaysIntervals)) {
            return "";
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        List<String> intervals = new ArrayList<>();
        for (ParkingSpotActiveIntervalData activeDaysInterval : activeDaysIntervals) {
            StringBuilder builder = new StringBuilder();
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

            intervals.add(builder.toString());
        }

        return StringUtils.arrayToCommaDelimitedString(intervals.toArray());
    }

    @Override
    public List<ParkingSpotActiveIntervalData> formatActiveDaysIntervals(String activeDaysIntervals) {
        if (StringUtils.isEmpty(activeDaysIntervals)) {
            return null;
        }

        List<ParkingSpotActiveIntervalData> activeDaysIntervalsList = new ArrayList<>();
        String[] array = activeDaysIntervals.split(ParkingSpotConstants.PARKING_SPOT_IDS_DELIMITER);
        for (int index = 0; index < array.length; index++) {
            ParkingSpotActiveIntervalData activeIntervalData = new ParkingSpotActiveIntervalData();

            String[] split = array[index].split("\\" + ParkingSpotConstants.PARKING_SPOT_ACTIVE_DAYS_INTERVALS_DAY_DELIMITER);
            if (split.length == 2) {
                try {
                    DayOfWeek dayOfWeek = DayOfWeek.valueOf(split[0]);

                    String[] interval = split[1].split(ParkingSpotConstants.PARKING_SPOT_ACTIVE_DAYS_INTERVALS_INTERVAL_DELIMITER);
                    if (interval.length == 2) {
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                        LocalTime startTime = LocalTime.parse(interval[0], dateTimeFormatter);
                        LocalTime endTime = LocalTime.parse(interval[1], dateTimeFormatter);

                        activeIntervalData.setDayOfWeek(dayOfWeek);
                        activeIntervalData.setStartTime(startTime);
                        activeIntervalData.setEndTime(endTime);

                        activeDaysIntervalsList.add(activeIntervalData);
                    }
                }
                catch (IllegalArgumentException e) {
                    //todo: Log error
                }
            }
        }

        return activeDaysIntervalsList;
    }

    @Override
    public List<ParkingSpot> findParkingSpotsInRadius(Float latitude, Float longitude, Integer radius) {
        //todo: Improve this
        List<ParkingSpot> parkingSpots = parkingSpotDao.findAll();

        List<ParkingSpot> parkingSpotsInRadius = new ArrayList<>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            double distance = GeolocationUtils.distance(latitude, parkingSpot.getLatitude(), longitude, parkingSpot.getLongitude(), 0.0, 0.0);
            if (distance <= radius) {
                parkingSpotsInRadius.add(parkingSpot);
            }
        }

        return parkingSpotsInRadius;
    }
}
