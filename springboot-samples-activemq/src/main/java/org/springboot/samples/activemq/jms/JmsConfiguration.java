package org.springboot.samples.activemq.jms;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfiguration {
	
	@Bean
	public Queue sampleQueue() {
		return new ActiveMQQueue("sample.queue");
	}

}
