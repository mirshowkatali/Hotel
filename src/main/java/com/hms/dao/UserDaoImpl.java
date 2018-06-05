package com.hms.dao;

import com.hms.helpers.Constant;
import com.hms.model.User;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User findById(int id) {
        User user = getByKey(id);
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    public User findByUsername(String username) {
        logger.info("Username : {}", username);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    public User findByEmail(String email) {
        logger.info("Email : {}", email);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("email", email));
        User user = (User) crit.uniqueResult();
        if (user != null) {
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<User> users = (List<User>) criteria.list();
        return users;
    }

    public void save(User user) {
        persist(user);
    }

    public void deleteByUsername(String username) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        User user = (User) crit.uniqueResult();
        delete(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllCustomers() {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.user_id FROM app_user_user_profile e WHERE e.user_profile_id = " + Constant.USER_ROLE.VERIFIED);
        List<Integer> customerIds = (List<Integer>) ids.list();
        List<User> customers = new ArrayList<User>();
        for (int i = 0; i < customerIds.size(); i++)
            customers.add(findById(customerIds.get(i)));
        return customers;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllAdmins() {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.user_id FROM app_user_user_profile e WHERE e.user_profile_id = " + Constant.USER_ROLE.ADMIN);
        List<Integer> adminIds = (List<Integer>) ids.list();
        List<User> admins = new ArrayList<User>();
        for (int i = 0; i < adminIds.size(); i++)
            admins.add(findById(adminIds.get(i)));
        return admins;
    }

    @SuppressWarnings("unchecked")
    public List<User> findAllManagers() {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.user_id FROM app_user_user_profile e WHERE e.user_profile_id = " + Constant.USER_ROLE.MANAGER);
        List<Integer> managerIds = (List<Integer>) ids.list();
        List<User> managers = new ArrayList<User>();
        for (int i = 0; i < managerIds.size(); i++)
            managers.add(findById(managerIds.get(i)));
        return managers;
    }

	public void updateUser1(String status) {
		SQLQuery ids = getSession().createSQLQuery("UPDATE  WHERE e.user_profile_id = " + Constant.USER_ROLE.VERIFIED);
		
	}
}
