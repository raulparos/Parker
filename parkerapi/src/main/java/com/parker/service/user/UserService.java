package com.parker.service.user;

import com.parker.domain.exception.user.UserException;
import com.parker.domain.model.User;

import java.util.Locale;

public interface UserService {
    Locale getCurrentLocale();

    User getCurrentUser() throws UserException;

    User authenticateUser(String email, String password) throws UserException;
}
