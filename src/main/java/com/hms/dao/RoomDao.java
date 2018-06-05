package com.hms.dao;

import com.hms.model.Room;

import java.util.List;

public interface RoomDao {

    Room findById(int id);

    Room findByName(String name);

    List<Room> findByTypeId(int id);

    List<Room> findByStatus(String status);

    void save(Room room);

    void deleteById(int id);

    void deleteByName(String name);

    List<Room> findAllRooms();
    
    List<Room> findRoom(String name);

    List<Room> findFreeRooms();
    
    List<Room> findFreeRooms(String type,String adults);

    List<Room> findFreeRooms(String type, String from, String to, String adults, String children, String rooms);
    
    List<Room> findFreeRooms(Integer min, Integer max, String type);

    List<Room> searchByName(String text);

}
