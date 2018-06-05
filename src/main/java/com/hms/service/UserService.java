package com.hms.service;

import com.hms.model.User;

import java.util.List;

public interface UserService {

    User findById(int id);

    User findByUsername(String username);

    User findByEmail(String email);

    void saveUser(User user);

    void updateUser(User user);
    
    void updateUser1(String status);

    void deleteUserByUsername(String username);

    List<User> findAllUsers();

    List<User> findAllCustomers();

    List<User> findAllAdmins();

    List<User> findAllManagers();

    boolean isUserUsernameUnique(Integer id, String username);

    boolean isUserEmailUnique(Integer id, String email);

    void sendConfirmationEmail(User user);

}