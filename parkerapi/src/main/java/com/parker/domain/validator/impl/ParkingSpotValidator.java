package com.parker.domain.validator.impl;

import com.parker.data.ParkingSpotData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ParkingSpotValidator implements Validator {
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
    }
}
