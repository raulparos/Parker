package com.parker.domain.validator.impl;

import com.parker.data.reservation.ReservationData;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;

@Component
public class ReservationValidator implements Validator {

    private static final Integer RESERVATION_NUMBER_OF_DAYS_IN_ADVANCE = 3;

    @Override
    public boolean supports(Class<?> clazz) {
        return ReservationData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReservationData reservationData = (ReservationData) target;

        ValidationUtils.rejectIfEmpty(errors, "day", "error.reservation.day.empty");
        ValidationUtils.rejectIfEmpty(errors, "startTime", "error.reservation.startTime.empty");
        ValidationUtils.rejectIfEmpty(errors, "endTime", "error.reservation.endTime.empty");

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, RESERVATION_NUMBER_OF_DAYS_IN_ADVANCE);
        Date maxDate = cal.getTime();
        if (reservationData.getDate().compareTo(maxDate) > 0) {
            String[] args = {RESERVATION_NUMBER_OF_DAYS_IN_ADVANCE.toString()};
            errors.rejectValue("day", "error.reservation.day.outOfBounds", args, "");
        }

        if (reservationData.getStartTime().isAfter(reservationData.getEndTime())) {
            errors.rejectValue("startTime", "error.reservation.interval.incorrect");
        }
    }
}
