package com.hms.converter;

import com.hms.model.User;
import com.hms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StringToUserConverter implements Converter<String, User> {

    static final Logger logger = LoggerFactory.getLogger(StringToUserConverter.class);

    @Autowired
    UserService userService;

    /**
     * Gets User by Id
     */
    public User convert(String element) {
        Integer id = Integer.parseInt((String) element);
        User user = userService.findById(id);
        logger.info("Username : {}", user.getUsername());
        return user;
    }

}