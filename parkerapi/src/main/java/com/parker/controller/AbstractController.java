package com.parker.controller;

import com.parker.data.ResponseContainer;
import com.parker.util.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

@Component
public abstract class AbstractController {
    @Autowired
    protected ValidatorUtils validatorUtils;

    protected boolean validate(Validator validator, Object object, BindingResult bindingResult, ResponseContainer responseContainer) {
        validator.validate(object, bindingResult);
        if (bindingResult.hasErrors()) {
            responseContainer.setSuccessful(false);
            responseContainer.setErrors(validatorUtils.getValidationErrors(bindingResult));

            return false;
        }

        return true;
    }
}
