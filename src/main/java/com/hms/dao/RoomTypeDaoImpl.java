package com.hms.dao;

import com.hms.model.RoomType;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roomTypeDao")
public class RoomTypeDaoImpl extends AbstractDao<Integer, RoomType> implements RoomTypeDao {

    public RoomType findById(int id) {
        return getByKey(id);
    }

    public RoomType findByType(String type) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("type", type));
        return (RoomType) crit.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<RoomType> findAll() {
        Criteria crit = createEntityCriteria();
        crit.addOrder(Order.asc("type"));
        return (List<RoomType>) crit.list();
    }

}