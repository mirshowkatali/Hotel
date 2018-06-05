package com.hms.helpers;

import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.hms.model.Booking;
import com.google.gson.Gson; 

 
@Component
public class MessageSender {
 
    @Autowired
    JmsTemplate jmsTemplate;
    private Destination destination;

    public void sendMessage(final String queueName,  final String message1) {
		System.out.println("Sending message " + message1 + "to queue - " + queueName);
		jmsTemplate.send(queueName, new MessageCreator() {

			public ObjectMessage  createMessage(Session session) throws JMSException {
                ObjectMessage message = session.createObjectMessage();
                message.setObject(message1);                     
                 return message;
			}
		});   
    }
 
}