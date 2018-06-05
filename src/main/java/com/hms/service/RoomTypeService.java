package com.hms.service;

import com.hms.model.RoomType;

import java.util.List;

public interface RoomTypeService {

    RoomType findById(int id);

    RoomType findByType(String type);

    List<RoomType> findAll();

}
