package com.parker.util;

import com.parker.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@Component
public final class ValidatorUtils {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UserService userService;

    public final List<String> getValidationErrors(BindingResult bindingResult) {
        List<String> errorsList = new ArrayList<>();
        for (ObjectError error : bindingResult.getAllErrors()) {
            String errorString;
            try {
                errorString = messageSource.getMessage(error.getCode(), error.getArguments(), userService.getCurrentLocale());
            }
            catch (NoSuchMessageException e) {
                //todo: Log the error
                errorString = error.getCode();
            }

            errorsList.add(errorString);
        }

        return errorsList;
    }
}
