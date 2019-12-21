package org.springboot.samples.activemq.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfiguration {

	@Bean
	public MappingJackson2MessageConverter converter(ObjectMapper mapper) {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setObjectMapper(mapper);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

}
