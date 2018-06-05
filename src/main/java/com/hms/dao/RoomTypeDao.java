package com.hms.dao;

import com.hms.model.RoomType;

import java.util.List;

public interface RoomTypeDao {

    List<RoomType> findAll();

    RoomType findByType(String type);

    RoomType findById(int id);

}