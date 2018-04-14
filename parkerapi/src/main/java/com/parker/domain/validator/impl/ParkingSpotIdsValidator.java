package com.parker.domain.validator.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ParkingSpotIdsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return String.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        String parkingSpotIds = (String) target;

        if (StringUtils.isEmpty(parkingSpotIds)) {
            errors.reject("error.parkingSpot.parkingSpotIds.empty");
        }
    }
}
