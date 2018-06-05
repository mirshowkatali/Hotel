package com.hms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.helpers.MessageSender;

@Service("messageService")
public class MessageServiceImpl implements MessageService{

	
	@Autowired
    MessageSender messageSender;
	
	
	public void sendMessage(final String ORDER_QUEUE,String message) {
		messageSender.sendMessage(ORDER_QUEUE,message);
		
	}

}
