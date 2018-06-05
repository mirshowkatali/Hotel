package com.hms.dao;

import com.hms.model.RoomImages;

import java.util.List;

public interface RoomImagesDao {

    RoomImages findById(int id);

    void save(RoomImages image);

    void deleteById(int id);

    void deleteByRoomId(int id);

    List<RoomImages> findByRoomId(int id);

    List<RoomImages> findAll();

}
