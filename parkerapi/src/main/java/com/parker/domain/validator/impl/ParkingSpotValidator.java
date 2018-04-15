package com.parker.domain.validator.impl;

import com.parker.data.ParkingSpotActiveIntervalData;
import com.parker.data.ParkingSpotData;
import com.parker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;

@Component
public class ParkingSpotValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ParkingSpotData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ParkingSpotData parkingSpotData = (ParkingSpotData) target;
        ValidationUtils.rejectIfEmpty(errors, "latitude", "error.parkingSpot.latitude.empty");
        ValidationUtils.rejectIfEmpty(errors, "longitude", "error.parkingSpot.longitude.empty");

        if (parkingSpotData.getLatitude() < -90 || parkingSpotData.getLongitude() > 90) {
            errors.reject("error.parkingSpot.latitude.outOfBounds");
        }

        if (parkingSpotData.getLongitude() < -180 || parkingSpotData.getLongitude() > 180) {
            errors.reject("error.parkingSpot.longitude.outOfBounds");
        }

        List<ParkingSpotActiveIntervalData> activeDaysIntervals = parkingSpotData.getActiveDaysIntervals();
        if (CollectionUtils.isEmpty(activeDaysIntervals)) {
            errors.reject("error.parkingSpot.activeDaysIntervals.empty");
        }
        else {
            for (ParkingSpotActiveIntervalData activeDaysInterval : activeDaysIntervals) {
                if (activeDaysInterval.getStartTime() == null ||
                        activeDaysInterval.getEndTime() == null ||
                        activeDaysInterval.getStartTime().isAfter(activeDaysInterval.getEndTime())) {
                    String[] args = {activeDaysInterval.getDayOfWeek().getDisplayName(TextStyle.FULL, userService.getCurrentLocale())};
                    errors.rejectValue("activeDaysIntervals", "error.parkingSpot.activeDaysIntervals.interval.incorrect", args, "");
                }
            }
        }
    }
}
