package com.hms.dao;

import java.util.List;

import com.hms.model.User;


public interface UserDao {

	User findById(int id);
	
	User findByUsername(String username);

	User findByEmail(String email);

	void save(User user);
	
	void deleteByUsername(String username);
	
	List<User> findAllUsers();

	List<User> findAllCustomers();

	List<User> findAllAdmins();

	List<User> findAllManagers();

	void updateUser1(String status);
}

