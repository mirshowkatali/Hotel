package com.hms.dao;

import com.hms.model.Booking;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookingDao")
public class BookingDaoImpl extends AbstractDao<Integer, Booking> implements BookingDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public Booking findById(int id) {
        Booking booking = getByKey(id);
        if (booking != null) {
            Hibernate.initialize(booking.getRoom());
            Hibernate.initialize(booking.getUser());
        }
        return booking;
    }

    public Booking findByRoomId(int id) {
        logger.info("Booking : {}", id);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("room_id", id));
        Booking booking = (Booking) crit.uniqueResult();
        if (booking != null) {
            Hibernate.initialize(booking.getRoom());
            Hibernate.initialize(booking.getUser());
        }
        return booking;
    }

    @SuppressWarnings("unchecked")
    public List<Booking> findByUserId(int id) {
        logger.info("User : {}", id);
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM booking e WHERE e.user_id = " + id);
        ids.addEntity(Booking.class);
        return (List<Booking>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<Booking> findByTypeId(int id) {
        logger.info("Type : {}", id);
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM booking e WHERE e.room_type_id = " + id);
        ids.addEntity(Booking.class);
        return (List<Booking>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<Booking> findAllBookings() {
        SQLQuery ids = getSession().createSQLQuery("SELECT d.* FROM booking d ORDER BY d.status DESC");
        ids.addEntity(Booking.class);
        return (List<Booking>) ids.list();
    }

    public void save(Booking booking) {
        persist(booking);
    }
 
    public void update(Booking booking) {
       update(booking);
    }
    public void deleteById(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Booking booking = (Booking) crit.uniqueResult();
        delete(booking);
    }

    public void deleteByRoomId(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("room_id", id));
        Booking booking = (Booking) crit.uniqueResult();
        delete(booking);
    }

    public void deleteByUserId(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("user_id", id));
        Booking booking = (Booking) crit.uniqueResult();
        delete(booking);
    }

    @SuppressWarnings("unchecked")
    public List<Booking> findPendingBookings() {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM booking e WHERE e.status = 'PENDING'");
        ids.addEntity(Booking.class);
        return (List<Booking>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<Booking> findConfirmedBookings() {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM booking e WHERE e.status = 'CONFIRMED'");
        ids.addEntity(Booking.class);
        return (List<Booking>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<Booking> findCompletedBookings() {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM booking e WHERE e.status = 'COMPLETED'");
        ids.addEntity(Booking.class);
        return (List<Booking>) ids.list();
    }
}
