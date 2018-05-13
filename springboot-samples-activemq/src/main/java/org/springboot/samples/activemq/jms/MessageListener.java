package org.springboot.samples.activemq.jms;

import org.apache.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	private Logger LOG = Logger.getLogger(getClass());

	@JmsListener(destination = "sample.queue")
	public void receiveQueue(String text) {
		LOG.info(text);
	}

}
