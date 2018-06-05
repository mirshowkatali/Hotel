package com.hms.service;

import com.hms.configuration.MailConfig;
import com.hms.model.Booking;
import com.hms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;
    @Autowired
    MailConfig mailConfig;
    private String website = "http://localhost:8080/hms";

    private void send(MimeMessagePreparator preparator) {
        try {
        	mailSender=mailConfig.getMailSender();
            mailSender.send(preparator);
            System.out.println("Message Sent");
        } catch (MailException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }

    public void sendConfirmationEmail(Object object) {
        User user = (User) object;
        MimeMessagePreparator preparator = getMessagePreparator(user);
        mailSender=mailConfig.getMailSender();
        mailSender.send(preparator);
        System.out.println("Message Sent");
    }

    public void sendBookingCreatedEmail(Object object) {
        Booking booking = (Booking) object;
        MimeMessagePreparator preparator = getPendingBookingMessagePreparator(booking);
        send(preparator);
    }

    public void sendBookingConfirmedEmail(Object object) {
        Booking booking = (Booking) object;
        MimeMessagePreparator preparator = getConfirmedBookingMessagePreparator(booking);
        send(preparator);
    }

    private MimeMessagePreparator getConfirmedBookingMessagePreparator(final Booking booking) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("abc@yahoo.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(booking.getUser().getEmail()));
                mimeMessage.setText(getConfirmedBookingBody(booking));
                mimeMessage.setSubject("Booking #" + booking.getId() + " Confirmed");
            }
        };
        return preparator;
    }

    private MimeMessagePreparator getPendingBookingMessagePreparator(final Booking booking) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("abc@yahoo.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(booking.getUser().getEmail()));
                mimeMessage.setText(getPendingBookingBody(booking));
                mimeMessage.setSubject("Booking #" + booking.getId() + " Created");
            }
        };
        return preparator;
    }

    private MimeMessagePreparator getMessagePreparator(final User user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("abc@yahoo.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(user.getEmail()));
                mimeMessage.setText(getConfirmationBody(user));
                mimeMessage.setSubject("Confirm email");
            }
        };
        return preparator;
    }

    private String getPendingBookingBody(final Booking booking) {
        return "Dear " + booking.getUser().getFirstName() + " " + booking.getUser().getLastName()
                + ", thank you for booking " + booking.getRoom().getName() + " at Hotel Management System." +
                "Your booking id is: " + booking.getId() + '\n'
                + " Please wait for confirmation of your booking.";
    }

    private String getConfirmedBookingBody(final Booking booking) {
        return "Dear " + booking.getUser().getFirstName() + " " + booking.getUser().getLastName()
                + ", your booking for " + booking.getRoom().getName() + " has been confirmed" +
                "Your booking id is: " + booking.getId() + '\n'
                + " Your arrival is on " + booking.getArrivalTime() + " & your departure is on " + booking.getDepartureTime() + ".";
    }

    private String getConfirmationBody(final User user) {
        return "Dear " + user.getFirstName() + " " + user.getLastName()
                + ", thank you for choosing our Hotel. Click link below to get (" + user.getUsername()
                + ") verified and start booking." + '\n' + generateConfirmationEmail(user.getUsername(), user.getToken());
    }

    private String generateConfirmationEmail(String username, String token) {
        return (website + "/user/profile-" + username + "/" + token + "/confirm");
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

}