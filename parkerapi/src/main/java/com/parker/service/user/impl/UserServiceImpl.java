package com.parker.service.user.impl;

import com.parker.data.UserLoginData;
import com.parker.domain.exception.UserException;
import com.parker.domain.model.User;
import com.parker.service.user.UserService;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class UserServiceImpl implements UserService {
    @Override
    public Locale getCurrentLocale() {
        //todo: Implement this correctly when localization comes into play. This is just a mock-up
        return Locale.ENGLISH;
    }

    @Override
    public User getCurrentUser() throws UserException {
        //todo: Implement this method
        return null;
    }

    @Override
    public User authenticateUser(UserLoginData userLoginData) throws UserException {
        //todo: Implement this method
        return null;
    }


}
