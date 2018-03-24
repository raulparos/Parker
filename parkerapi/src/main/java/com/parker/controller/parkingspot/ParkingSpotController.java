package com.parker.controller.parkingspot;

import com.parker.data.ParkingSpotData;
import com.parker.data.ResponseContainer;
import com.parker.domain.validator.impl.ParkingSpotValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotValidator parkingSpotValidator;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseContainer addParkingSpot(@RequestBody ParkingSpotData parkingSpotData, BindingResult bindingResult) {
        ResponseContainer responseContainer = new ResponseContainer();
        responseContainer.setData(parkingSpotData);

        getParkingSpotValidator().validate(parkingSpotData, bindingResult);

        return responseContainer;
    }

    public ParkingSpotValidator getParkingSpotValidator() {
        return parkingSpotValidator;
    }

}
