package com.hms.service;

import com.hms.dao.RoomImagesDao;
import com.hms.model.RoomImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roomImagesService")
@Transactional
public class RoomImagesServiceImpl implements RoomImagesService {

    @Autowired
    private RoomImagesDao dao;

    public RoomImages findById(int id) {
        return dao.findById(id);
    }

    public List<RoomImages> findByRoomId(int id) {
        return dao.findByRoomId(id);
    }

    public List<RoomImages> findAll() {
        return dao.findAll();
    }

    public void save(RoomImages image) {
        dao.save(image);
    }

    public void deleteById(int id) {
        dao.deleteById(id);
    }

    public void deleteByRoomId(int id) {
        dao.deleteByRoomId(id);
    }

    public void update(RoomImages image) {
        RoomImages entity = dao.findById(image.getId());
        if (entity != null) {
            entity.setRoom(image.getRoom());
            entity.setName(image.getName());
        }
    }
}