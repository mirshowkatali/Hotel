package com.hms.controller;

import com.hms.helpers.Constant;
import com.hms.model.User;
import com.hms.model.UserProfile;
import com.hms.service.BookingService;
import com.hms.service.UserProfileService;
import com.hms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class CustomerController {

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    BookingService bookingService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    /**
     * Register Page
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            User user = new User();
            model.addAttribute("user", user);
            model.addAttribute("edit", false);
            model.addAttribute("loggedinuser", getPrincipal());
            return "register";
        } else {
            return "redirect:/";
        }
    }

    /**
     * Validates & Register New Customer
     */
    @RequestMapping(value = "/regis", method = RequestMethod.POST)
    public String saveUser( @ModelAttribute User user, BindingResult result,
                           HttpServletRequest request, HttpServletResponse response,
                           ModelMap model) {

        if (result.hasErrors()) {
            return "register";
        }

        if (!userService.isUserUsernameUnique(user.getId(), user.getUsername())) {
            FieldError usernameError = new FieldError("user", "username", messageSource.getMessage("non.unique.username", new String[]{user.getUsername()}, Locale.getDefault()));
            result.addError(usernameError);
            return "register";
        } else if (!userService.isUserEmailUnique(user.getId(), user.getEmail())) {
            FieldError emailError = new FieldError("user", "email", messageSource.getMessage("non.unique.email", new String[]{user.getEmail()}, Locale.getDefault()));
            result.addError(emailError);
            return "register";
        }

        try {
            // Setting UNVERIFIED User role
            com.hms.model.UserProfile role = userProfileService.findById(Constant.USER_ROLE.UNVERIFIED);
            Set<com.hms.model.UserProfile> userProfile = new HashSet<UserProfile>();
            userProfile.add(role);
            user.setUserProfiles(userProfile);
            user.setToken(UUID.randomUUID().toString());
            userService.sendConfirmationEmail(user);
            userService.saveUser(user);
            //userService.updateUser1("VERIFIED");
            model.addAttribute("success", "User " + user.getFirstName() + " " + user.getLastName() + " registered successfully");
            model.addAttribute("loggedinuser", getPrincipal());

            //Requesting LOGIN
            request.login(user.getUsername(), user.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "success";
    }

    @RequestMapping(value = "/user/profile-{username}/resend")
    public String resendConfirmationMail(@PathVariable("username") String username, ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            return "redirect:/login";
        }

        User user = getCurrentUser();
        if (user.getUsername().equals(username)) {
            user.setToken(UUID.randomUUID().toString());
            userService.sendConfirmationEmail(user);
            userService.updateUser(user);
        }
        return "redirect:/";
    }

   /* @RequestMapping(value = "/user/profile", method = RequestMethod.POST)
    public String updateUser( @ModelAttribute User user, BindingResult result, ModelMap model) {
        if (isCurrentAuthenticationAnonymous()) {
            return "redirect:/login";
        }

        User user1 = getCurrentUser();
        if (user1.getUsername().equals(user.getUsername())) {
            user.setToken(UUID.randomUUID().toString());
           // userService.sendConfirmationEmail(user);
            userService.updateUser(user);
        }
        return "success";
    }*/
    
    @RequestMapping(value = "/user/profile-{username}/{token}/confirm")
    public String confirmUser(@PathVariable("username") String username, @PathVariable("token") String token,
                              ModelMap model, RedirectAttributes redirectAttributes) {
        if (isCurrentAuthenticationAnonymous()) {
            return "redirect:/login";
        }

        User user = getCurrentUser();
        if (user.getUsername().equals(username)) {
            if (user.getToken().equals(token)) {
                user.setToken(null);
                com.hms.model.UserProfile role = userProfileService.findById(Constant.USER_ROLE.VERIFIED);
                Set<com.hms.model.UserProfile> userProfile = new HashSet<UserProfile>();
                userProfile.add(role);
                user.setUserProfiles(userProfile);
                userService.updateUser(user);
                updateCurrentUser(user);
            } else {
                redirectAttributes.addFlashAttribute("success", "Confirmation expired. Kindly resend confirmation email");
            }
        } else {
            redirectAttributes.addFlashAttribute("success", "Kindly login with same account to confirm.");
        }
        return "redirect:/user/profile";
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

}