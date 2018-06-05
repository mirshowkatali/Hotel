package com.hms.service;

import com.hms.dao.BookingDao;
import com.hms.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookingService")
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingDao dao;

    @Autowired
    private MailService mailService;

    public Booking findById(int id) {
        return dao.findById(id);
    }

    public Booking findByRoomId(int id) {
        return dao.findByRoomId(id);
    }

    public List<Booking> findByUserId(int id) {
        return dao.findByUserId(id);
    }

    public List<Booking> findByTypeId(int id) {
        return dao.findByTypeId(id);
    }

    public void saveBooking(Booking booking) {
        dao.save(booking);
    }

    public void updateBooking(Booking booking) {
        Booking entity = dao.findById(booking.getId());
        if (entity != null) {
            entity.setUser(booking.getUser());
            entity.setRoom(booking.getRoom());
            entity.setPeople(booking.getPeople());
            entity.setArrivalTime(booking.getArrivalTime());
            entity.setDepartureTime(booking.getDepartureTime());
            entity.setComment(booking.getComment());
            entity.setStatus(booking.getStatus());
            entity.setRoomBooked(booking.getRoomBooked());
           // dao.update(entity);
        }
    }

    public void deleteBookingById(int id) {
        dao.deleteById(id);
    }

    public void deleteBookingByRoomId(int id) {
        dao.deleteByRoomId(id);
    }

    public void deleteBookingByUserId(int id) {
        dao.deleteByUserId(id);
    }

    public List<Booking> findAllBookings() {
        return dao.findAllBookings();
    }

    public boolean isBookingUnique(Integer id, Integer roomId) {
        Booking booking = findByRoomId(roomId);
        return (booking == null || ((id != null) && (booking.getId() == id)));
    }

    public List<Booking> findPendingBookings() {
        return dao.findPendingBookings();
    }

    public List<Booking> findConfirmedBookings() {
        return dao.findConfirmedBookings();
    }

    public List<Booking> findCompletedBookings() {
        return dao.findCompletedBookings();
    }

    public void sendBookingCreatedEmail(Booking booking) {
        mailService.sendBookingCreatedEmail(booking);
    }

    public void sendBookingConfirmedEmail(Booking booking) {
        mailService.sendBookingConfirmedEmail(booking);
    }
}