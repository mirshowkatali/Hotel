package com.hms.dao;

import com.hms.helpers.Constant;
import com.hms.model.Room;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roomDao")
public class RoomDaoImpl extends AbstractDao<Integer, Room> implements RoomDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public Room findById(int id) {
        Room room = getByKey(id);
        if (room != null) {
            Hibernate.initialize(room.getType());
        }
        return room;
    }

    public Room findByName(String name) {
        logger.info("Name : {}", name);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        Room room = (Room) crit.uniqueResult();
        if (room != null) {
            Hibernate.initialize(room.getType());
        }
        return room;
    }

    @SuppressWarnings("unchecked")
    public List<Room> findByTypeId(int id) {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM room e WHERE e.type = " + id);
        ids.addEntity(Room.class);
        return (List<Room>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<Room> findByStatus(String status) {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM room e WHERE e.status LIKE :check GROUP BY e.type");
        ids.setParameter("check", status);
        ids.addEntity(Room.class);
        return (List<Room>) ids.list();
    }

    public void save(Room room) {
        persist(room);
    }

    public void deleteByName(String name) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        Room room = (Room) crit.uniqueResult();
        delete(room);
    }

    public void deleteById(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Room room = (Room) crit.uniqueResult();
        Query ids = getSession().createQuery("DELETE FROM RoomImages a WHERE  a.room.id= :check");
        Query ids1 = getSession().createQuery("DELETE FROM Room  WHERE id= :check");
        Query ids2 = getSession().createQuery("DELETE FROM Booking a  WHERE a.room.id= :check");
       ids.setParameter("check", id);
       ids1.setParameter("check", id);
       ids2.setParameter("check", id);
       int i=ids.executeUpdate();
        int j=ids1.executeUpdate();
        int k=ids2.executeUpdate();
       // delete(room);
    }

    @SuppressWarnings("unchecked")
    public List<Room> findAllRooms() {
        SQLQuery ids = getSession().
                createSQLQuery("SELECT m.* from ROOM m WHERE m.status LIKE :check ORDER BY m.type");
        ids.setParameter("check", Constant.ROOM_STATUS.VERIFIED);
        ids.addEntity(Room.class);
        return (List<Room>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<Room> findFreeRooms() {
        SQLQuery ids = getSession().
                createSQLQuery("SELECT DISTINCT r.* FROM room r LEFT OUTER JOIN booking d ON d.room_id = r.id WHERE (d.room_id IS NULL) or (d.status LIKE :check)");
        ids.setParameter("check", Constant.BOOKING_STATUS.COMPLETED);
        ids.addEntity(Room.class);
        return (List<Room>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<Room> findFreeRooms(Integer min, Integer max, String type) {
        SQLQuery ids = null;
        if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.FAMILY.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT m.* from ROOM m JOIN (SELECT DISTINCT d.* FROM room d LEFT OUTER JOIN booking c ON c.room_id = d.id WHERE (c.room_id IS NULL) ORDER BY d.name) as t ON m.id = t.id WHERE m.status LIKE :check and m.price >= :minimum and m.price <= :maximum and m.type = :type");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.FAMILY);
        } else if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.DELUXE.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT m.* from ROOM m JOIN (SELECT DISTINCT d.* FROM room d LEFT OUTER JOIN booking c ON c.room_id = d.id WHERE (c.room_id IS NULL) ORDER BY d.name) as t ON m.id = t.id WHERE m.status LIKE :check and m.price >= :minimum and m.price <= :maximum and m.type = :type");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.DELUXE);
        } else if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.EXECUTIVE.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT m.* from ROOM m JOIN (SELECT DISTINCT d.* FROM room d LEFT OUTER JOIN booking c ON c.room_id = d.id WHERE (c.room_id IS NULL) ORDER BY d.name) as t ON m.id = t.id WHERE m.status LIKE :check and m.price >= :minimum and m.price <= :maximum and m.type = :type");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.EXECUTIVE);
        } else {
            ids = getSession().
                    createSQLQuery("SELECT m.* from ROOM m JOIN (SELECT DISTINCT d.* FROM room d LEFT OUTER JOIN booking c ON c.room_id = d.id WHERE (c.room_id IS NULL) ORDER BY d.name) as t ON m.id = t.id WHERE m.status LIKE :check and m.price >= :minimum and m.price <= :maximum");
        }
        ids.setParameter("check", Constant.ROOM_STATUS.VERIFIED);
        ids.setParameter("minimum", min);
        ids.setParameter("maximum", max);
        ids.addEntity(Room.class);
        return (List<Room>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<Room> searchByName(String text) {
        SQLQuery query = getSession().createSQLQuery("SELECT m.* FROM room m JOIN (SELECT e.* FROM room e WHERE e.name LIKE :check) as t ON m.id = t.id WHERE m.status LIKE :verify");
        query.setParameter("check", "%" + text + "%");
        query.setParameter("verify", Constant.ROOM_STATUS.VERIFIED);
        query.addEntity(Room.class);
        return (List<Room>) query.list();
    }

	public List<Room> findFreeRooms(String type, String from, String to, String adults, String children, String rooms) {
		SQLQuery ids = null;
		int adt=Integer.valueOf(adults);
        if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.FAMILY.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT DISTINCT r.* FROM room r LEFT OUTER JOIN booking d ON d.room_id = r.id WHERE (d.room_id IS NULL and  r.type = :type) or (d.status LIKE :check and (d.arrival_time >=:from and d.departure_time >=:to) and d.people=:adt ) and  r.type = :type ");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.FAMILY);
        } else if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.DELUXE.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT DISTINCT r.* FROM room r LEFT OUTER JOIN booking d ON d.room_id = r.id WHERE (d.room_id IS NULL and  r.type = :type) or (d.status LIKE :check  and (d.arrival_time >=:from and d.departure_time >=:to) and d.people=:adt ) and  r.type = :type ");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.DELUXE);
        } else if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.EXECUTIVE.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT DISTINCT r.* FROM room r LEFT OUTER JOIN booking d ON d.room_id = r.id WHERE (d.room_id IS NULL and  r.type = :type) or (d.status LIKE :check and (d.arrival_time >=:from and d.departure_time >=:to) and d.people=:adt ) and  r.type = :type ");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.EXECUTIVE);
        } 
        ids.setParameter("check", Constant.BOOKING_STATUS.COMPLETED);
        ids.setParameter("from", from);
        ids.setParameter("to", to);
        ids.setParameter("adt", adt);
        ids.addEntity(Room.class);
        return (List<Room>) ids.list();
	}
	
	public List<Room> findFreeRooms(String type,String adults) {
		SQLQuery ids = null;
		int adt=Integer.valueOf(adults);
        if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.FAMILY.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT DISTINCT r.* FROM room r LEFT OUTER JOIN booking d ON d.room_id = r.id WHERE (d.room_id IS NULL and  r.type = :type) or (d.status LIKE :check  and d.people=:adt ) and  r.type = :type  ");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.FAMILY);
        } else if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.DELUXE.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT DISTINCT r.* FROM room r LEFT OUTER JOIN booking d ON d.room_id = r.id WHERE (d.room_id IS NULL and  r.type = :type) or (d.status LIKE :check  and d.people=:adt ) and  r.type = :type  ");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.DELUXE);
        } else if (type != null && type.toLowerCase().equals(Constant.ROOM_TYPE.EXECUTIVE.toLowerCase())) {
            ids = getSession().
                    createSQLQuery("SELECT DISTINCT r.* FROM room r LEFT OUTER JOIN booking d ON d.room_id = r.id WHERE (d.room_id IS NULL and  r.type = :type) or (d.status LIKE :check  and d.people=:adt ) and  r.type = :type  ");
            ids.setParameter("type", Constant.ROOM_TYPE_VALUE.EXECUTIVE);
        } 
        ids.setParameter("adt", adt);      
        ids.setParameter("check", Constant.BOOKING_STATUS.COMPLETED);
        ids.addEntity(Room.class);
        return (List<Room>) ids.list();
	}

	public List<Room> findRoom(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}