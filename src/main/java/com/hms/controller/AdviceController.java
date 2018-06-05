package com.hms.controller;

import com.hms.validator.BookingValidator;
import com.hms.validator.RoomValidator;
import com.hms.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle(Exception ex) {
        return "redirect:/errors/404";
    }

    @Autowired
    RoomValidator roomValidator;

    @Autowired
    BookingValidator bookingValidator;

    @Autowired
    UserValidator userValidator;

    @InitBinder("user")
    protected void initUserBinder(WebDataBinder binder) {
        binder.setValidator(new UserValidator());
    }

    @InitBinder("room")
    protected void initRoomBinder(WebDataBinder binder) {
        binder.setValidator(new RoomValidator());
    }

    @InitBinder("booking")
    protected void initBookingBinder(WebDataBinder binder) {
        binder.setValidator(new BookingValidator());
    }

    public UserValidator getUserValidator() {
        return userValidator;
    }

    public RoomValidator getRoomValidator() {
        return roomValidator;
    }

    public void setRoomValidator(RoomValidator roomValidator) {
        this.roomValidator = roomValidator;
    }

    public BookingValidator getBookingValidator() {
        return bookingValidator;
    }

    public void setBookingValidator(BookingValidator bookingValidator) {
        this.bookingValidator = bookingValidator;
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;


    }
}