package com.hms.validator;

import com.hms.model.Booking;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.hms.helpers.InputValidatorHelpers.isHtmlSafe;

@Component
public class BookingValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return Booking.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        Booking booking = (Booking) target;

        if (isHtmlSafe(booking.getComment())) {
            errors.rejectValue("comment", "field.notallowed");
        }
        if (isHtmlSafe(booking.getArrivalTime().toString()) && (booking.getArrivalTime().toString().indexOf("<") >= 0
                || booking.getArrivalTime().toString().indexOf(">") >= 0 || booking.getDepartureTime().toString().indexOf("=") >= 0)) {
            errors.rejectValue("arrivalTime", "field.notallowed");
        }
        if (isHtmlSafe(booking.getDepartureTime().toString()) && (booking.getDepartureTime().toString().indexOf("<") >= 0
                || booking.getDepartureTime().toString().indexOf(">") >= 0 || booking.getDepartureTime().toString().indexOf("=") >= 0)) {
            errors.rejectValue("departureTime", "field.notallowed");
        }
        if (isHtmlSafe(booking.getPeople().toString())) {
            errors.rejectValue("people", "field.notallowed");
        }


        /**
         * Booking checks
         */


        // Error handling
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date currentTime = null;
        try {
            currentTime = (Date) dateFormat.parse(dateFormat.format(cal.getTime()));
        } catch (ParseException p) {
            p.printStackTrace();
        }

        if (currentTime != null) {
            if (booking.getArrivalTime().before(currentTime)) {
                errors.rejectValue("arrivalTime", "non.error.before_arrival");
            } else if (booking.getDepartureTime().before(currentTime)) {
                errors.rejectValue("departureTime", "non.error.before_departure");
            }
        }
        if (booking.getArrivalTime().after(booking.getDepartureTime())) {
            errors.rejectValue("arrivalTime", "non.error.arrival_after");
            errors.rejectValue("departureTime", "non.error.departure_before");
        }
    }
}