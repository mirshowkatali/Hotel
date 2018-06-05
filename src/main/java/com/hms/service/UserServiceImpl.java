package com.hms.service;

import com.hms.dao.UserDao;
import com.hms.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public User findById(int id) {
        return dao.findById(id);
    }

    public User findByUsername(String username) {
        User user = dao.findByUsername(username);
        return user;
    }

    public User findByEmail(String email) {
        User user = dao.findByEmail(email);
        return user;
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if (entity != null) {
            entity.setUsername(user.getUsername());
            if (!user.getPassword().equals(entity.getPassword())) {
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setUserProfiles(user.getUserProfiles());
            entity.setToken(user.getToken());
  
        }
    }

    public void deleteUserByUsername(String username) {
        dao.deleteByUsername(username);
    }

    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    public boolean isUserUsernameUnique(Integer id, String username) {
        User user = findByUsername(username);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

    public boolean isUserEmailUnique(Integer id, String email) {
        User user = findByEmail(email);
        return (user == null || ((id != null) && (user.getId() == id)));
    }

    public List<User> findAllManagers() {
        return dao.findAllManagers();
    }

    public List<User> findAllCustomers() {
        return dao.findAllCustomers();
    }

    public List<User> findAllAdmins() {
        return dao.findAllAdmins();
    }

    public void sendConfirmationEmail(User user) {
        mailService.sendConfirmationEmail(user);
    }

	public void updateUser1(String status) {
		dao.updateUser1(status);
		
	}
}
