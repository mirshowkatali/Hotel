package com.hms.service;

import com.hms.dao.RoomTypeDao;
import com.hms.model.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roomTypeService")
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    @Autowired
    RoomTypeDao dao;

    public RoomType findById(int id) {
        return dao.findById(id);
    }

    public RoomType findByType(String type) {
        return dao.findByType(type);
    }

    public List<RoomType> findAll() {
        return dao.findAll();
    }
}

