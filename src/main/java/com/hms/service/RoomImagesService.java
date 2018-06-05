package com.hms.service;

import com.hms.model.RoomImages;

import java.util.List;

public interface RoomImagesService {

    RoomImages findById(int id);

    List<RoomImages> findByRoomId(int id);

    List<RoomImages> findAll();

    void save(RoomImages image);

    void deleteById(int id);

    void deleteByRoomId(int id);

    void update(RoomImages image);

}
