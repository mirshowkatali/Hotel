package com.hms.service;

import com.hms.model.Booking;

import java.util.List;

public interface BookingService {

    Booking findById(int id);

    List<Booking> findByUserId(int id);

    Booking findByRoomId(int id);

    List<Booking> findByTypeId(int id);

    void saveBooking(Booking booking);

    void updateBooking(Booking booking);

    void deleteBookingById(int id);

    void deleteBookingByRoomId(int id);

    void deleteBookingByUserId(int id);

    List<Booking> findAllBookings();

    List<Booking> findPendingBookings();

    List<Booking> findConfirmedBookings();

    List<Booking> findCompletedBookings();

    boolean isBookingUnique(Integer id, Integer roomId);

    void sendBookingCreatedEmail(Booking booking);

    void sendBookingConfirmedEmail(Booking booking);

}
