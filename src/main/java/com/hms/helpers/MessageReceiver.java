package com.hms.helpers;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;

import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.hms.model.Booking;
 

 
@Component
public class MessageReceiver  {
    
	
	private static final String ORDER_QUEUE = "order-queue"; 
    
     
     
   
	@Autowired
	JmsTemplate jmsTemplate;
	
	
	
	public String receive() throws JMSException{
		Message receivedMessage=jmsTemplate.receive(ORDER_QUEUE);
		ObjectMessage msg = (ObjectMessage)receivedMessage;
        System.out.println("Message Received :"+msg.getObject().toString());
        return msg.getObject().toString();
	}
}