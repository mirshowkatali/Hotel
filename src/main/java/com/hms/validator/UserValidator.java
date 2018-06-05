package com.hms.validator;

import com.hms.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.hms.helpers.InputValidatorHelpers.isHtmlSafe;

@Component
public class UserValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "user.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "user.name.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.username.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "user.email.empty");

        if (isHtmlSafe(user.getFirstName())) {
            errors.rejectValue("firstName", "field.notallowed");
        }
        if (isHtmlSafe(user.getLastName())) {
            errors.rejectValue("lastName", "field.notallowed");
        }
        if (isHtmlSafe(user.getUsername())) {
            errors.rejectValue("username", "field.notallowed");
        }
        if (isHtmlSafe(user.getEmail())) {
            errors.rejectValue("email", "field.notallowed");
        }
        if (!user.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            errors.rejectValue("email", "user.email.invalid");
        }
    }
}
