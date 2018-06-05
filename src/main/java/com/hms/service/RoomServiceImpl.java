package com.hms.service;

import com.hms.dao.RoomDao;
import com.hms.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao dao;

    public Room findById(int id) {
        return dao.findById(id);
    }

    public Room findByName(String name) {
        return dao.findByName(name);
    }

    public List<Room> findByTypeId(int id) {
        return dao.findByTypeId(id);
    }

    public List<Room> findByStatus(String status) {
        return dao.findByStatus(status);
    }

    public void saveRoom(Room room) {
        dao.save(room);
    }

    public void updateRoom(Room room) {
        Room entity = dao.findById(room.getId());
        if (entity != null) {
            entity.setName(room.getName());
            entity.setCapacity(room.getCapacity());
            entity.setDescription(room.getDescription());
            entity.setBath(room.getBath());
            entity.setBed(room.getBed());
            entity.setInternet(room.getInternet());
            entity.setType(room.getType());
            entity.setStatus(room.getStatus());
        }
    }

    public void deleteRoomById(int id) {
        dao.deleteById(id);
    }

    public void deleteRoomByName(String name) {
        dao.deleteByName(name);
    }

    public List<Room> findAllRooms() {
        return dao.findAllRooms();
    }

    public boolean isRoomNameUnique(Integer id, String name) {
        Room room = findByName(name);
        return (room == null || ((id != null) && (room.getId().equals(id))));
    }

    public List<Room> findFreeRooms() {
        return dao.findFreeRooms();
    }
    
    public List<Room> findFreeRooms(String type, String from, String to, String adults, String children, String rooms) {
        return dao.findFreeRooms(type,from,to,adults,children,rooms);
    }
    
    public List<Room> findFreeRooms(String type, String adults) {
        return dao.findFreeRooms(type,adults);
    }
    public List<Room> findFreeRooms(Integer min, Integer max, String type) {
        return dao.findFreeRooms(min, max, type);
    }

    public List<Room> searchByName(String text) {
        return dao.searchByName(text);
    }

	public List<Room> findRoom(String name) {
		
		return dao.findRoom(name);
	}

}
