package com.hms.dao;

import com.hms.model.RoomImages;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;

@Repository("roomImagesDao")
public class RoomImagesDaoImpl extends AbstractDao<Integer, RoomImages> implements RoomImagesDao {

    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public RoomImages findById(int id) {
        RoomImages roomImages = getByKey(id);
        if (roomImages != null) {
            Hibernate.initialize(roomImages.getRoom());
        }
        return roomImages;
    }

    @SuppressWarnings("unchecked")
    public List<RoomImages> findByRoomId(int id) {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM room_image e WHERE e.room = " + id);
        ids.addEntity(RoomImages.class);
        return (List<RoomImages>) ids.list();
    }

    @SuppressWarnings("unchecked")
    public List<RoomImages> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<RoomImages>) criteria.list();
    }

    public void save(RoomImages image) {
        persist(image);
    }

    public void deleteById(int id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        RoomImages roomImages = (RoomImages) crit.uniqueResult();
        deleteImage(roomImages);
    }

    @SuppressWarnings("unchecked")
    public void deleteByRoomId(int id) {
        SQLQuery ids = getSession().createSQLQuery("SELECT e.* FROM room_image e WHERE e.room = " + id);
        ids.addEntity(RoomImages.class);
        List<RoomImages> roomImages = (List<RoomImages>) ids.list();

        for (int i = 0; i < roomImages.size(); i++)
            deleteImage(roomImages.get(i));
    }

    private void deleteImage(RoomImages roomImages) {
        final String path = "C:\\Users\\Adil\\IdeaProjects\\Hyatt\\src\\main\\webapp\\static\\images\\rooms\\";
        File file = new File(path + roomImages.getName());
        while (file.exists() && !file.isDirectory()) {
            if (file.delete()) {
                delete(roomImages);
            }
        }
    }
}
