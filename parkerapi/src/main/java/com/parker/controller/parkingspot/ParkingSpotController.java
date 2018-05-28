package com.parker.controller.parkingspot;

import com.parker.constants.ParkingSpotConstants;
import com.parker.controller.AbstractController;
import com.parker.data.ResponseContainer;
import com.parker.data.parkingspot.FilterData;
import com.parker.data.parkingspot.ParkingSpotData;
import com.parker.data.parkingspot.ParkingSpotFreeIntervalData;
import com.parker.domain.validator.impl.ParkingSpotIdsValidator;
import com.parker.domain.validator.impl.ParkingSpotValidator;
import com.parker.facade.parkingspot.ParkingSpotFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/parking-spot")
public class ParkingSpotController extends AbstractController {

    @Autowired
    private ParkingSpotValidator parkingSpotValidator;

    @Autowired
    private ParkingSpotIdsValidator parkingSpotIdsValidator;

    @Autowired
    private ParkingSpotFacade parkingSpotFacade;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseContainer addParkingSpot(@RequestBody ParkingSpotData parkingSpotData, BindingResult bindingResult) {
        ResponseContainer responseContainer = new ResponseContainer();

        if (!validate(parkingSpotValidator, parkingSpotData, bindingResult, responseContainer)) {
            return responseContainer;
        }

        parkingSpotData = parkingSpotFacade.addParkingSpot(parkingSpotData);
        responseContainer.setData(parkingSpotData);

        return responseContainer;
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    public ResponseContainer getParkingSpots(@RequestParam("parkingSpotIds") String parkingSpotIds) {
        ResponseContainer responseContainer = new ResponseContainer();

        BindException bindException = new BindException(parkingSpotIds, "parkingSpotIds");
        if (!validate(parkingSpotIdsValidator, parkingSpotIds, bindException, responseContainer)) {
            return responseContainer;
        }

        List<ParkingSpotData> parkingSpots = parkingSpotFacade.getParkingSpots(parkingSpotIds);
        String[] idsSplit = parkingSpotIds.split(ParkingSpotConstants.PARKING_SPOT_IDS_DELIMITER);
        if (idsSplit.length == 1) {
            //If the request param contained only one parking spot id, the response should contain only the parking spot
            if (parkingSpots.size() == 1) {
                responseContainer.setData(parkingSpots.get(0));
            }
        } else {
            responseContainer.setData(parkingSpots);
        }

        return responseContainer;
    }

    @ResponseBody
    @RequestMapping(value = "/get-in-radius", method = RequestMethod.GET, produces = "application/json")
    public ResponseContainer getParkingSpotsInRange(@RequestParam("latitude") Float latitude, @RequestParam("longitude") Float longitude,
                                                    @RequestParam(name = "radius", defaultValue = "1500") Integer radius) {
        ResponseContainer responseContainer = new ResponseContainer();
        List<ParkingSpotData> parkingSpotsInRadius = parkingSpotFacade.findParkingSpotsInRadius(latitude, longitude, radius);

        responseContainer.setData(parkingSpotsInRadius);

        return responseContainer;
    }

    @ResponseBody
    @RequestMapping(value = "/get-for-user", method = RequestMethod.GET, produces = "application/json")
    public ResponseContainer getParkingSpotsForUser() {
        ResponseContainer responseContainer = new ResponseContainer();

        List<ParkingSpotData> parkingSpotsForUser = parkingSpotFacade.findParkingSpotsForCurrentUser();
        responseContainer.setData(parkingSpotsForUser);

        return responseContainer;
    }

    @ResponseBody
    @RequestMapping(value = "/get-in-radius", method = RequestMethod.POST, produces = "application/json")
    public ResponseContainer getFilteredParkingSpotsInRange(@RequestBody FilterData filterData) {
        ResponseContainer responseContainer = new ResponseContainer();
        List<ParkingSpotData> parkingSpotsInRadius = parkingSpotFacade.findFilteredParkingSpotsInRadius(filterData);

        responseContainer.setData(parkingSpotsInRadius);

        return responseContainer;
    }

    @ResponseBody
    @RequestMapping(value = "/{parkingSpotId}/get-free-intervals", method = RequestMethod.GET, produces = "application/json")
    public ResponseContainer getParkingSpotFreeIntervals(@PathVariable("parkingSpotId") String parkingSpotId, @RequestParam("date") String date) {
        ResponseContainer responseContainer = new ResponseContainer();
        try {
            List<ParkingSpotFreeIntervalData> freeIntervals = parkingSpotFacade.getFreeIntervals(parkingSpotId, date);
            responseContainer.setData(freeIntervals);
        } catch (ParseException e) {
            //todo: Log error
            responseContainer.setSuccessful(false);
            List<String> errors = new ArrayList<>();
            errors.add("The date format is not correct");
            responseContainer.setErrors(errors);
        }


        return responseContainer;
    }
}
