package com.parker.controller.reservation;

import com.parker.controller.AbstractController;
import com.parker.data.ResponseContainer;
import com.parker.data.reservation.ReservationData;
import com.parker.domain.exception.reservation.ReservationException;
import com.parker.domain.validator.impl.ReservationValidator;
import com.parker.facade.reservation.ReservationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/reservation")
public class ReservationController extends AbstractController {

    @Autowired
    private ReservationValidator reservationValidator;

    @Autowired
    private ReservationFacade reservationFacade;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseContainer addReservation(@RequestBody ReservationData reservationData, BindingResult bindingResult) {
        ResponseContainer responseContainer = new ResponseContainer();

        if (!validate(reservationValidator, reservationData, bindingResult, responseContainer)) {
            return responseContainer;
        }

        try {
            reservationData = reservationFacade.addReservation(reservationData);
            responseContainer.setData(reservationData);
        } catch (ReservationException e) {
            //todo: Log error
            responseContainer.setSuccessful(false);
            responseContainer.setErrors(new ArrayList<>().add(e.getMessage()));
        }

        return responseContainer;
    }
}
