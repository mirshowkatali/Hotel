package com.hms.converter;

import com.hms.model.RoomType;
import com.hms.service.RoomTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TypeToRoomTypeConverter implements Converter<Object, RoomType> {

    static final Logger logger = LoggerFactory.getLogger(TypeToRoomTypeConverter.class);

    @Autowired
    RoomTypeService roomTypeService;

    /**
     * Gets RoomType by Id
     */
    public RoomType convert(Object element) {
        Integer id = Integer.parseInt((String) element);
        RoomType type = roomTypeService.findById(id);
        logger.info("Type : {}", type);
        return type;
    }

}