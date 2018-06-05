package com.hms.validator;

import com.hms.model.Room;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import static com.hms.helpers.InputValidatorHelpers.isHtmlSafe;

@Component
public class RoomValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return Room.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        Room room = (Room) target;

        ValidationUtils.rejectIfEmpty(errors, "name", "room.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "price", "room.price.empty");
        ValidationUtils.rejectIfEmpty(errors, "capacity", "room.capacity.empty");

        if (isHtmlSafe(room.getDescription())) {
            errors.rejectValue("description", "field.notallowed");
        }
        if (isHtmlSafe(room.getName())) {
            errors.rejectValue("name", "field.notallowed");
        }
        if (isHtmlSafe(room.getBed().toString())) {
            errors.rejectValue("bed", "field.notallowed");
        }
        if (isHtmlSafe(room.getCapacity().toString())) {
            errors.rejectValue("capacity", "field.notallowed");
        }
        if (isHtmlSafe(room.getPrice().toString())) {
            errors.rejectValue("price", "field.notallowed");
        }
    }
}
