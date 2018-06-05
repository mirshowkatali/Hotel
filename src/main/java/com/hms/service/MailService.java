package com.hms.service;

public interface MailService {

    void sendConfirmationEmail(Object object);

    void sendBookingCreatedEmail(Object object);

    void sendBookingConfirmedEmail(Object object);

}