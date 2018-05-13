package org.springboot.samples.activemq.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
	
	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	public void send(String destination, String payload) throws MessagingException {
		jmsMessagingTemplate.convertAndSend(destination, payload);;
	}
	
	

}
