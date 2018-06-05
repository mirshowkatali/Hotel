package com.hms.converter;

import com.hms.model.Room;
import com.hms.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StringToRoomConverter implements Converter<String, Room> {

    static final Logger logger = LoggerFactory.getLogger(StringToRoomConverter.class);

    @Autowired
    RoomService roomService;

    /**
     * Gets Room by Id
     */
    public Room convert(String element) {
        Integer id = Integer.parseInt((String) element);
        Room room = roomService.findById(id);
        logger.info("Room : {}", room.getName());
        return room;
    }

}