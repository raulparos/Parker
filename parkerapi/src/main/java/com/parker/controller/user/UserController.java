package com.parker.controller.user;

import com.parker.data.ResponseContainer;
import com.parker.data.UserLoginData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseContainer login(@RequestBody UserLoginData userData) {
        ResponseContainer responseContainer = new ResponseContainer();

        return responseContainer;
    }
}
