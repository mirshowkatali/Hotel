package com.hms.service;

import com.hms.model.Room;

import java.util.List;

public interface RoomService {

    Room findById(int id);

    Room findByName(String name);

    List<Room> findByTypeId(int id);

    List<Room> findByStatus(String status);

    void saveRoom(Room room);

    void updateRoom(Room room);

    void deleteRoomById(int id);

    void deleteRoomByName(String name);

    boolean isRoomNameUnique(Integer id, String name);

    List<Room> findAllRooms();

    List<Room> findRoom(String name);
    
    List<Room> findFreeRooms();
    
    List<Room> findFreeRooms(String type,String adults);
    
    List<Room> findFreeRooms(String type, String from, String to, String adults, String children, String rooms);

    List<Room> findFreeRooms(Integer min, Integer max, String type);

    List<Room> searchByName(String text);

}