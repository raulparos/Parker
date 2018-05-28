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
import java.util.List;

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
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            responseContainer.setErrors(errors);
        }

        return responseContainer;
    }

    @ResponseBody
    @RequestMapping(value = "/get-for-user", method = RequestMethod.GET, produces = "application/json")
    public ResponseContainer getReservationsForUser() {
        ResponseContainer responseContainer = new ResponseContainer();

        responseContainer.setData(reservationFacade.findReservationsForUser());

        return responseContainer;
    }

    @ResponseBody
    @RequestMapping(value = "/{reservationId}/delete", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseContainer deleteReservation(@PathVariable("reservationId") String reservationId) {
        ResponseContainer responseContainer = new ResponseContainer();

        reservationFacade.deleteReservation(reservationId);

        return responseContainer;
    }
}
