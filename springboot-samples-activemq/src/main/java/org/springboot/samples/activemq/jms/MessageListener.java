package org.springboot.samples.activemq.jms;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;

@Service
public class MessageListener {

	private Logger LOG = Logger.getLogger(getClass());

	@Autowired
	private MappingJackson2MessageConverter converter;

	@JmsListener(destination = "sample.queue")
	public void onMessage(Message message) throws JMSException {
		converter.fromMessage(message);
		LOG.info(message);
	}

}
