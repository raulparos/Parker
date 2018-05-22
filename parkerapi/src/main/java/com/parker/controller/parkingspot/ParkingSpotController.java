package com.parker.controller.parkingspot;

import com.parker.constants.ParkingSpotConstants;
import com.parker.controller.AbstractController;
import com.parker.data.FilterData;
import com.parker.data.ParkingSpotData;
import com.parker.data.ResponseContainer;
import com.parker.domain.validator.impl.ParkingSpotIdsValidator;
import com.parker.domain.validator.impl.ParkingSpotValidator;
import com.parker.facade.parkingspot.ParkingSpotFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
                                                    @RequestParam(value = "radius", defaultValue = "1500") Integer radius) {
        ResponseContainer responseContainer = new ResponseContainer();
        List<ParkingSpotData> parkingSpotsInRadius = parkingSpotFacade.findParkingSpotsInRadius(latitude, longitude, radius);

        responseContainer.setData(parkingSpotsInRadius);

        return responseContainer;
    }

    @ResponseBody
    @RequestMapping(value = "/get-in-radius", method = RequestMethod.POST, produces = "application/json")
    public ResponseContainer getParkingSpotsInRange(@RequestBody FilterData filterData) {
        ResponseContainer responseContainer = new ResponseContainer();


        return responseContainer;
    }
}
