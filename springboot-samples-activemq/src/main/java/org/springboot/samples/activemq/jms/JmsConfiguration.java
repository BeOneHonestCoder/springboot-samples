package org.springboot.samples.activemq.jms;

import javax.jms.Queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfiguration {
	
	@Bean
	public Queue sampleQueue() {
		return new ActiveMQQueue("sample.queue");
	}

	@Bean
	public MappingJackson2MessageConverter converter(ObjectMapper mapper) {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setObjectMapper(mapper);
		return converter;
	}

}
