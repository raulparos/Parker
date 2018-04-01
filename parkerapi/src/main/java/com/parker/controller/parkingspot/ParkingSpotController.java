package com.parker.controller.parkingspot;

import com.parker.controller.AbstractController;
import com.parker.data.ParkingSpotData;
import com.parker.data.ResponseContainer;
import com.parker.domain.validator.impl.ParkingSpotValidator;
import com.parker.facade.parkingspot.ParkingSpotFacade;
import com.parker.util.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/parking-spot")
public class ParkingSpotController extends AbstractController {

    @Autowired
    private ParkingSpotValidator parkingSpotValidator;

    @Autowired
    private ParkingSpotFacade parkingSpotFacade;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseContainer addParkingSpot(@RequestBody ParkingSpotData parkingSpotData, BindingResult bindingResult) {
        ResponseContainer responseContainer = new ResponseContainer();

        if (!validate(getParkingSpotValidator(), parkingSpotData, bindingResult, responseContainer)) {
            return responseContainer;
        }

        parkingSpotData = parkingSpotFacade.addParkingSpot(parkingSpotData);
        responseContainer.setData(parkingSpotData);

        return responseContainer;
    }

    public ParkingSpotValidator getParkingSpotValidator() {
        return parkingSpotValidator;
    }

}
