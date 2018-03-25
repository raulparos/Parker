package com.parker.service.user.impl;

import com.parker.dao.user.UserDao;
import com.parker.domain.exception.user.UserException;
import com.parker.domain.exception.user.UserNotFoundException;
import com.parker.domain.model.User;
import com.parker.service.user.UserService;
import com.parker.util.authentication.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Locale getCurrentLocale() {
        //todo: Implement this correctly when localization comes into play. This is just a mock-up
        return Locale.ENGLISH;
    }

    @Override
    public User getCurrentUser() {
        //todo: Implement this method
        return null;
    }

    @Override
    public User authenticateUser(String email, String password) throws UserException {
        User user = userDao.find(email, UserUtils.encryptPassword(password));
        if (user == null) {
            throw new UserNotFoundException("The email/password combination does not exist");
        }

        return user;
    }

}
