package com.parker.controller.user;

import com.parker.controller.AbstractController;
import com.parker.data.ResponseContainer;
import com.parker.data.user.UserLoginData;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController extends AbstractController {

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseContainer login(@RequestBody UserLoginData userData) {
        ResponseContainer responseContainer = new ResponseContainer();

        return responseContainer;
    }
}
