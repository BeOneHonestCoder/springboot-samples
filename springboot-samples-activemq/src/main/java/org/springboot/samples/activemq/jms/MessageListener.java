package org.springboot.samples.activemq.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class MessageListener {

	private Logger LOG = LoggerFactory.getLogger(MessageListener.class);

	@Autowired
	private MappingJackson2MessageConverter converter;

	@JmsListener(destination = "jms.test.queue")
	public void onMessage(Message message) throws JMSException {
		String text = (String) converter.fromMessage(message);
		LOG.info("" + text);
	}

}
