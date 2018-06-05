package com.hms.controller;

import com.hms.helpers.Constant;
import com.hms.helpers.MessageReceiver;
import com.hms.model.Booking;
import com.hms.model.User;
import com.hms.model.UserProfile;
import com.hms.service.BookingService;
import com.hms.service.MessageService;
import com.hms.service.RoomTypeService;
import com.hms.service.UserProfileService;
import com.hms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes("roles")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    BookingService bookingService;

    @Autowired
    MessageSource messageSource;
    
    @Autowired
    MessageService messageService;
    
    @Autowired
    MessageReceiver messageReceive;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
    
    private static final String ORDER_QUEUE = "order-queue";
    
    private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";
    
    @RequestMapping(value = {"/", "/home"})
    public String home(ModelMap model) {
        model.addAttribute("familybase", roomTypeService.findById(Constant.ROOM_TYPE_VALUE.FAMILY).getBasePrice());
        model.addAttribute("executivebase", roomTypeService.findById(Constant.ROOM_TYPE_VALUE.EXECUTIVE).getBasePrice());
        model.addAttribute("deluxebase", roomTypeService.findById(Constant.ROOM_TYPE_VALUE.DELUXE).getBasePrice());
        return "index";
    }

    /**
     * @param username - optional
     * @param email    - optional
     * @link /register/available?username=username&email=email
     */
    @RequestMapping(value = "/user/availability")
    public
    @ResponseBody
    String checkUserAvailability(@RequestParam(value = "username", defaultValue = "") String username,
                                 @RequestParam(value = "email", defaultValue = "") String email) {
        User user = null;
        if (username != null && !username.equals("")) user = userService.findByUsername(username);
        if (email != null && !email.equals("")) user = userService.findByEmail(email);
        if (user == null) return "Available";
        else return "Not Available";
    }


    // ERRORS

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/errors/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "accessDenied";
    }

    @RequestMapping(value = {"/errors/404"})
    public String pageNotFound() {
        return "404";
    }
  
    
    @RequestMapping(value = {"/gallery"})
    public String gallery() {
        return "gallery";
    }
    
    @RequestMapping(value = {"/testimonial"})
    public String testimonial() {
        return "testimonial";
    }
    
    @RequestMapping(value = {"/tariff"})
    public String tariff() {
        return "tariff";
    }
    
    @RequestMapping(value = {"/facilities"})
    public String facilities() {
        return "facilities";
    }
    
    @RequestMapping(value = {"/sendMessage"})
    public String sendMessage() {
        return "sMessage";
    }
    
    @RequestMapping(value = {"/receiveMessage"})
    public String receiveMessage() {
        return "rMessage";
    }
    
    @RequestMapping(value = { "/send" }, method = RequestMethod.POST)
    public String sendMessage(@RequestParam("message")String message, 
            ModelMap model) {
        
        messageService.sendMessage(ORDER_QUEUE,message);
        model.addAttribute("success", "Message" + " registered.");
        return "sMessage";
    }
    
    @RequestMapping(value = { "/receive" }, method = RequestMethod.GET)
    public String receiveMessage(ModelMap model) throws JMSException {
        
		//Message message="hi";
     String msg= messageReceive.receive();
       model.addAttribute("msg", msg);
        return "rMessage";
    }
    
    /**
     * This method handles login GET requests.
     * If users is already logged-in and tries to goto login page again, will be redirected to list page.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public String profile(ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            User user = getCurrentUser();
            model.addAttribute("username", getPrincipal());
            model.addAttribute("user", user);
            model.addAttribute("totalbookings", user.getBookings().size());
            return "profile";
        }
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute User user, BindingResult result,
                                ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {

            if (!userService.isUserUsernameUnique(user.getId(), user.getUsername())) {
                FieldError usernameError = new FieldError("user", "username", messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
                result.addError(usernameError);
                return "profile";
            } else if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
                FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email", new String[]{user.getEmail()}, Locale.getDefault()));
                result.addError(emailError);
                return "profile";
            }

            User n = getCurrentUser();
            n.setFirstName(user.getFirstName());
            n.setLastName(user.getLastName());
            n.setUsername(user.getUsername());
            if (!(n.getEmail().equals(user.getEmail()))) {
                n.setEmail(user.getEmail());

                boolean check = false;
                for (UserProfile profile : n.getUserProfiles())
                    if (profile.getId().equals(Constant.USER_ROLE.ADMIN) ||
                            profile.getId().equals(Constant.USER_ROLE.MANAGER))
                        check = true;

                if (!check) {
                    com.hms.model.UserProfile role = userProfileService.findById(Constant.USER_ROLE.UNVERIFIED);
                    Set<UserProfile> userProfile = new HashSet<UserProfile>();
                    userProfile.add(role);
                    n.setUserProfiles(userProfile);
                    n.setToken(UUID.randomUUID().toString());
                  //  userService.sendConfirmationEmail(n);
                }
            }
            userService.updateUser(n);
            updateCurrentUser(n);
            model.addAttribute("success", "Your profile was updated successfully");
            return "profile";
        }
    }

    @RequestMapping(value = "/user/profile-loggedin+edit", method = RequestMethod.POST)
    public String updatePassword(@Valid @ModelAttribute("user") User user, @RequestParam("newpassword") String password,
                                 BindingResult result, ModelMap model, RedirectAttributes redirectAttributes) {
        User n = getCurrentUser();
        n.setPassword(password);
        userService.updateUser(n);
        redirectAttributes.addFlashAttribute("success", "Your password was changed successfully");
        return "redirect:/user/profile";
    }

    @RequestMapping(value = "/user/profile/cancel-{id}+confirmed")
    public String deleteBooking(@PathVariable int id, ModelMap model, RedirectAttributes redirectAttributes) {

        if (bookingService.findById(id).getStatus().equals(Constant.BOOKING_STATUS.COMPLETED)) {
            redirectAttributes.addFlashAttribute("success", "Booking No " + id + " has been completed already, it can not be cancelled.");
            return "redirect:/user/profile";
        }

        Integer booking = id;
        bookingService.deleteBookingById(id);
        redirectAttributes.addFlashAttribute("success", "Your Booking (" + booking + ") was cancelled successfully");
        return "redirect:/user/profile";
    }

    @RequestMapping(value = "/user/profile/delete")
    public String deleteUser(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String username = getCurrentUser().getUsername();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        persistentTokenBasedRememberMeServices.logout(request, response, auth);
        SecurityContextHolder.getContext().setAuthentication(null);
        userService.deleteUserByUsername(username);
        return "redirect:/";
    }

    /**
     * This method handles logout requests.
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            //new SecurityContextLogoutHandler().logout(request, response, auth);
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/";
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

    private void updateCurrentUser(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, getGrantedAuthorities(user));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (UserProfile userProfile : user.getUserProfiles())
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
        return authorities;
    }


    /**
     * Generic Mapping
     */
    @RequestMapping(value = "/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping(value = "/about")
    public String about() {
        return "about";
    }
}