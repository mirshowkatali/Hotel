package com.hms.controller;

import com.hms.helpers.JavaIntegrationKit2;
import com.hms.helpers.Constant;
import com.hms.model.Booking;
import com.hms.model.Room;
import com.hms.model.RoomType;
import com.hms.model.User;
import com.hms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("roles")
public class HotelController {

    @Autowired
    UserService userService;

    @Autowired
    RoomService roomService;

    @Autowired
    BookingService bookingService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public String rooms(ModelMap model) {
        List<Room> rooms = roomService.findAllRooms();
        model.addAttribute("rooms", rooms);
        model.addAttribute("search", false);
        return "rooms";
    }

    @RequestMapping(value = "/room", method = RequestMethod.GET)
    public String room(@RequestParam("name") String name,ModelMap model) {
        Room rom = roomService.findByName(name);
        model.addAttribute("rom", rom);
        model.addAttribute("search", false);
        return "room";
    }

    
    @RequestMapping(value = "/rooms/search", method = RequestMethod.GET)
    public String searchRooms(@RequestParam("searchtext") String text,ModelMap model) {
        List<Room> rooms;
        if (text.toLowerCase().contains("fam".toLowerCase())
                || text.toLowerCase().contains(Constant.ROOM_TYPE.FAMILY.toLowerCase()))
            rooms = roomService.findByTypeId(Constant.ROOM_TYPE_VALUE.FAMILY);
        else if (text.toLowerCase().contains("del".toLowerCase())
                || text.toLowerCase().contains(Constant.ROOM_TYPE.DELUXE.toLowerCase()))
            rooms = roomService.findByTypeId(Constant.ROOM_TYPE_VALUE.DELUXE);
        else if (text.toLowerCase().contains("exe".toLowerCase())
                || text.toLowerCase().contains(Constant.ROOM_TYPE.EXECUTIVE.toLowerCase()))
            rooms = roomService.findByTypeId(Constant.ROOM_TYPE_VALUE.EXECUTIVE);
        else if (text.toLowerCase().contains("free".toLowerCase())
                || text.toLowerCase().contains("avail".toLowerCase())
                || text.toLowerCase().contains("free rooms".toLowerCase())
                || text.toLowerCase().contains("available".toLowerCase()))
            rooms = roomService.findFreeRooms();
        else if (text.toLowerCase().contains("all".toLowerCase())
                || text.toLowerCase().contains("all rooms".toLowerCase()))
            rooms = roomService.findAllRooms();
        else rooms = roomService.searchByName(text);
        model.addAttribute("rooms", rooms);
        model.addAttribute("searchtext", text);
        model.addAttribute("search", true);
        return "rooms";
    }

    @RequestMapping(value = "/rooms/search1", method = RequestMethod.GET)
    public String searchRooms1(@RequestParam("searchtext") String text, @RequestParam("txtFrom") String from, @RequestParam("txtTo") String to, @RequestParam("group_adults") String adults,  @RequestParam("no_rooms") String room, ModelMap model) {
        List<Room> rooms=null;
        String children="no children";
        if (text.toLowerCase().contains(Constant.ROOM_TYPE.FAMILY.toLowerCase()))
            rooms = roomService.findFreeRooms(Constant.ROOM_TYPE.FAMILY,from,to,adults,children,room);
        else if (text.toLowerCase().contains(Constant.ROOM_TYPE.DELUXE.toLowerCase()))
            rooms = roomService.findFreeRooms(Constant.ROOM_TYPE.DELUXE,from,to,adults,children,room);
        else if (text.toLowerCase().contains(Constant.ROOM_TYPE.EXECUTIVE.toLowerCase()))
            rooms = roomService.findFreeRooms(Constant.ROOM_TYPE.EXECUTIVE,from,to,adults,children,room);
        
        model.addAttribute("rooms", rooms);
        model.addAttribute("searchtext", text);
        model.addAttribute("search", true);
        return "rooms";
    }
    /**
     * This method handles the Booking
     */
    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public String book(ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            return "redirect:/login";
        } else {
            Booking booking = new Booking();
            model.addAttribute("booking", booking);
            addBookingAttributes(model);
            return "booking";
        }
    }

    @RequestMapping(value = "/booking-{id}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public String bookSpecificRoom(@PathVariable Integer id, @RequestParam("type") String type, @RequestParam("adults") String adults,ModelMap model,HttpServletRequest request) {
        if (isCurrentAuthenticationAnonymous()) {
            return "redirect:/login";
        } else {
            Room room = roomService.findById(id);
            HttpSession session=request.getSession();
            session.setAttribute("type", type);
           // if (room.getBooking() != null || roomService.findById(id) == null)
             //   return "redirect:/rooms";
            int adt=2;
            if(adults.equals("")) {
            	adt=2;
            }else {
              adt=Integer.valueOf(adults);
            }
            Booking booking = new Booking();
            booking.setRoom(room);
            booking.setPeople(adt);
           // booking.setStatus(status);
            model.addAttribute("booking", booking);
            addBookingAttributesSession(model,booking,session);
            return "booking";
        }
    }
    @RequestMapping(value = "/payU_Home", method = RequestMethod.GET)
	public String payU_Home(Locale locale, @ModelAttribute Booking booking, BindingResult result,
            HttpServletRequest request, HttpServletResponse response,
            ModelMap model) {
		//logger.info("Welcome payU_Home! the client locale is "+ locale.toString());
    	if (result.hasErrors()) {
            addBookingAttributes(model);
            return "booking";
        }
    	if (booking.getUser() == null) booking.setUser(getCurrentUser());
    	HttpSession session=request.getSession();
    	session.setAttribute("booking", booking);
        
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("booking", booking);
		return "payU_Home";
	}

	@RequestMapping(value = "/payU_Process", method = RequestMethod.GET)
	public String payU_Process(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		//logger.info("Welcomess payU_Process! the client locale is "+ locale.toString());
		
		JavaIntegrationKit2 integrationKit = new JavaIntegrationKit2();
        try {
			Map<String, String> values = integrationKit.hashCalMethod(request, response);
			System.out.println(values.get("hash"));

			model.addAttribute("values", values);
        } catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Booking booking=(Booking)session.getAttribute("booking");
        try {
        	if (booking.getUser() == null) booking.setUser(getCurrentUser());
            booking.setStatus(Constant.BOOKING_STATUS.PENDING);
            bookingService.saveBooking(booking);
            bookingService.sendBookingCreatedEmail(booking);
			System.out.println("Payment "+request.getParameter("status"));
			
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "payU_Process";
	}
	
	@RequestMapping(value = "/payU_Success", method = RequestMethod.POST)
	public String payU_Success(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
	//	logger.info("Welcomess payU_Success! the client locale is "+ locale.toString());
		
		
		
		return "payU_Success";
	}
	
	@RequestMapping(value = "/payU_Fail", method = RequestMethod.POST)
	public String payU_Fail(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) {
		//logger.info("Welcomess payU_Fail! the client locale is "+ locale.toString());
		
        try {
        	System.out.println("Payment "+request.getParameter("status"));
        	
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "payU_Fail";
	}
    @RequestMapping(value = "/booking", method = RequestMethod.POST)
    public String bookRoom(@Valid @ModelAttribute Booking booking, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response,
                           ModelMap model) {

        if (result.hasErrors()) {
            addBookingAttributes(model);
            return "booking";
        }

        if (booking.getUser() == null) booking.setUser(getCurrentUser());
        booking.setStatus(Constant.BOOKING_STATUS.PENDING);
        bookingService.saveBooking(booking);
        bookingService.sendBookingCreatedEmail(booking);
        model.addAttribute("success", "Booking ID " + booking.getId() + " created successfully");
        model.addAttribute("bookingsuccess", booking.getId());
        return "success";
    }

    private void addBookingAttributes(ModelMap model) {
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("rooms", roomService.findFreeRooms());
        model.addAttribute("edit", false);
    }

    /**
     * This method will provide RoomType list to views
     */
    @ModelAttribute("types")
    public List<RoomType> initializeTypes() {
        return roomTypeService.findAll();
    }

    private void addBookingAttributes(ModelMap model, Booking booking) {
    	
        model.addAttribute("booking", booking);
        model.addAttribute("user", getCurrentUser());
        model.addAttribute("users", userService.findAllUsers());
        model.addAttribute("rooms", roomService.findFreeRooms());
        model.addAttribute("loggedinuser", getPrincipal());
        model.addAttribute("edit", true);
    }

    private void addBookingAttributesSession(ModelMap model, Booking booking, HttpSession session) {
   	
       model.addAttribute("booking", booking);
       model.addAttribute("user", getCurrentUser());
       model.addAttribute("users", userService.findAllUsers());
       model.addAttribute("rooms", roomService.findFreeRooms(session.getAttribute("type").toString(),booking.getPeople().toString()));
       model.addAttribute("loggedinuser", getPrincipal());
       model.addAttribute("edit", true);
   }
    @RequestMapping(value = "/manage/bookings/edit-{id}", method = RequestMethod.GET)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public String booking(ModelMap model, @PathVariable Integer id, RedirectAttributes redirectAttrs) {
        if (isCurrentAuthenticationAnonymous()) {
            return "redirect:/login";
        } else {

            if (bookingService.findById(id) == null) {
                redirectAttrs.addFlashAttribute("success", "There is no booking with Id " + id + " in system.");
                return "redirect:/manage";
            }

            Booking booking = bookingService.findById(id);
            model.addAttribute("booking", booking);
            model.addAttribute("user", getCurrentUser());
            model.addAttribute("users", userService.findAllUsers());
            model.addAttribute("rooms", roomService.findAllRooms());
            model.addAttribute("loggedinuser", getPrincipal());
            model.addAttribute("edit", true);
            return "editBooking";
        }
    }

    @RequestMapping(value = "/manage/bookings/edit-{id}", method = RequestMethod.POST)
    public String updateBooking(@ModelAttribute Booking booking, BindingResult result,
                                ModelMap model, @PathVariable Integer id, RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            addBookingAttributes(model, booking);
            return "editBooking";
        }

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
                FieldError dateError = new FieldError("booking", "arrivalTime",
                        messageSource.getMessage("non.error.before_arrival", new Date[]{booking.getArrivalTime()}, Locale.getDefault()));
                result.addError(dateError);
                addBookingAttributes(model, booking);
                return "editBooking";
            } else if (booking.getDepartureTime().before(currentTime)) {
                FieldError dateError = new FieldError("booking", "departureTime",
                        messageSource.getMessage("non.error.before_departure", new Date[]{booking.getDepartureTime()}, Locale.getDefault()));
                result.addError(dateError);
                addBookingAttributes(model, booking);
                return "editBooking";
            }
        }
        if (booking.getArrivalTime().after(booking.getDepartureTime())) {
            FieldError dateError = new FieldError("booking", "arrivalTime",
                    messageSource.getMessage("non.error.arrival_after", new Date[]{booking.getArrivalTime()}, Locale.getDefault()));
            FieldError dateError_ = new FieldError("booking", "departureTime",
                    messageSource.getMessage("non.error.departure_before", new Date[]{booking.getDepartureTime()}, Locale.getDefault()));
            result.addError(dateError);
            result.addError(dateError_);
            addBookingAttributes(model, booking);
            return "editBooking";
        }

        bookingService.updateBooking(booking);
        redirectAttrs.addFlashAttribute("success", "Booking No " + id + " was updated successfully");
        return "redirect:/manage";
    }

    @RequestMapping(value = "/bookings/print-{id}", method = RequestMethod.GET)
    public String printBooking(@ModelAttribute Booking booking, BindingResult result,
                                ModelMap model, @PathVariable Integer id, RedirectAttributes redirectAttrs) {
    	Booking booking1 = bookingService.findById(id);
        if (result.hasErrors()) {
            addBookingAttributes(model, booking);
            
            return "printBooking";
        }
        model.addAttribute(booking1);
        
        redirectAttrs.addFlashAttribute("success", "Booking No " + id + " was printed successfully");
        return "printBooking";
    }
    
    @RequestMapping(value = "/manage/bookings/delete-{id}")
    public String deleteBooking(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        if (bookingService.findById(id) == null) {
            redirectAttrs.addFlashAttribute("success", "Booking No " + id + " does not exist in database.");
            return "redirect:/manage";
        }

        if (bookingService.findById(id).getStatus().equals(Constant.BOOKING_STATUS.COMPLETED)) {
            redirectAttrs.addFlashAttribute("success", "Booking No " + id + " has been completed, it can't be removed from database.");
            return "redirect:/manage";
        }

        Integer booking = id;
        bookingService.deleteBookingById(id);
        redirectAttrs.addFlashAttribute("success", "Booking No " + booking + " was deleted successfully");
        return "redirect:/manage";
    }

    @RequestMapping(value = "/manage/bookings/confirm-{id}")
    public String confirmBooking(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        Booking booking = bookingService.findById(id);

        if (booking == null) {
            redirectAttrs.addFlashAttribute("success", "Booking No " + id + " does not exist in database.");
            return "redirect:/manage";
        }

        if (booking.getStatus().equals(Constant.BOOKING_STATUS.COMPLETED)) {
            redirectAttrs.addFlashAttribute("success", "Booking No " + booking + " has been completed, it can't be set to CONFIRMED.");
            return "redirect:/manage";
        }
        booking.setStatus(Constant.BOOKING_STATUS.CONFIRMED);
        bookingService.updateBooking(booking);
        bookingService.sendBookingConfirmedEmail(booking);
        redirectAttrs.addFlashAttribute("success", "Booking No " + id + " was set to confirmed");
        return "redirect:/manage";
    }

    @RequestMapping(value = "/manage/bookings/complete-{id}")
    public String completeBooking(@PathVariable Integer id, RedirectAttributes redirectAttrs) {
        Booking booking = bookingService.findById(id);

        if (booking == null) {
            redirectAttrs.addFlashAttribute("success", "Booking No " + id + " does not exist in database.");
            return "redirect:/manage";
        }

        booking.setRoomBooked(booking.getRoom().getName());
        booking.setStatus(Constant.BOOKING_STATUS.COMPLETED);
       // booking.setRoom(null);
        bookingService.updateBooking(booking);
        redirectAttrs.addFlashAttribute("success", "Booking No " + id + " was set to completed");
        return "redirect:/manage";
    }

    @RequestMapping(value = "/manage/rooms/edit-{id}", method = RequestMethod.POST)
    public String updateRoomPrice(@PathVariable Integer id, @RequestParam("price") Integer roomPrice,
                                  RedirectAttributes redirectAttributes) {
        Room room = roomService.findById(id);
        room.setPrice(roomPrice);
        room.setStatus(Constant.ROOM_STATUS.VERIFIED);
        roomService.updateRoom(room);
        redirectAttributes.addFlashAttribute("success", "Room " + id + " has been updated and verified.");
        return "redirect:/manage";
    }

    @RequestMapping(value = "/manage/rooms/verify-{id}")
    public String verifyRoom(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Room room = roomService.findById(id);
        room.setStatus(Constant.ROOM_STATUS.VERIFIED);
        roomService.updateRoom(room);
        redirectAttributes.addFlashAttribute("success", "Room " + id + " has been verified.");
        return "redirect:/manage";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else if (principal instanceof User) {
            userName = ((User) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    private User getCurrentUser() {
        return userService.findByUsername(getPrincipal());
    }

    /**
     * This method returns true if users is already authenticated [logged-in]
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}
