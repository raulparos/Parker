package com.parker.service.user;

import com.parker.data.UserLoginData;
import com.parker.domain.exception.UserException;
import com.parker.domain.model.User;

import java.util.Locale;

public interface UserService {
    Locale getCurrentLocale();
    User getCurrentUser() throws UserException;
    User authenticateUser(UserLoginData userLoginData) throws UserException;
}
