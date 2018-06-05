package com.hms.controller;

import com.hms.model.Booking;
import com.hms.model.Room;
import com.hms.model.User;
import com.hms.service.BookingService;
import com.hms.service.RoomService;
import com.hms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Transactional
public class ServiceController {

    @Autowired
    RoomService roomService;

    @Autowired
    UserService userService;

    @Autowired
    BookingService bookingService;

    /**
     * @param available - optional
     * @param min       - optional
     * @param max       - optional
     * @param type      - optional
     * @link /rooms/find?available=true&type=family&minprice=0&maxprice=3000;
     */
    @RequestMapping("/rooms/find")
    public
    @ResponseBody
    List<Room> getAvailableRooms(@RequestParam(value = "available", defaultValue = "true") boolean available,
                                 @RequestParam(value = "minprice", defaultValue = "0") Integer min,
                                 @RequestParam(value = "maxprice", defaultValue = "0") Integer max,
                                 @RequestParam(value = "type", defaultValue = "") String type) {
        return roomService.findFreeRooms(min, max, type);
    }

    /**
     * @param email - optional
     * @link /booking/user?email=user@email.com
     */
    @RequestMapping("/booking/user")
    public List<Booking> getUserBookings(@RequestParam(value = "email", defaultValue = "") String email) {
        User user = userService.findByEmail(email);
        if (user == null) return null;
        return bookingService.findByUserId(user.getId());
    }

}
