package com.parker.controller.parkingspot;

import com.parker.data.ParkingSpotData;
import com.parker.data.ResponseContainer;
import com.parker.domain.validator.impl.ParkingSpotValidator;
import com.parker.util.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/parking-spot")
public class ParkingSpotController {

    @Autowired
    private ParkingSpotValidator parkingSpotValidator;

    @Autowired
    private ValidatorUtils validatorUtils;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseContainer addParkingSpot(@RequestBody ParkingSpotData parkingSpotData, BindingResult bindingResult) {
        ResponseContainer responseContainer = new ResponseContainer();

        getParkingSpotValidator().validate(parkingSpotData, bindingResult);
        if (bindingResult.hasErrors()) {
            responseContainer.setSuccessful(false);
            responseContainer.setErrors(validatorUtils.getValidationErrors(bindingResult));

            return responseContainer;
        }



        return responseContainer;
    }

    public ParkingSpotValidator getParkingSpotValidator() {
        return parkingSpotValidator;
    }

}
