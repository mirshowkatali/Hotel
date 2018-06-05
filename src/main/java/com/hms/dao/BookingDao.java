package com.hms.dao;

import com.hms.model.Booking;

import java.util.List;

public interface BookingDao {

    Booking findById(int id);

    List<Booking> findByUserId(int id);

    Booking findByRoomId(int id);

    List<Booking> findByTypeId(int id);

    void save(Booking booking);
    
    void update(Booking booking);

    void deleteById(int id);

    void deleteByUserId(int id);

    void deleteByRoomId(int id);

    List<Booking> findAllBookings();

    List<Booking> findPendingBookings();

    List<Booking> findConfirmedBookings();

    List<Booking> findCompletedBookings();

}
