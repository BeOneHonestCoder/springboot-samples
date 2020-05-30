package org.springboot.samples.activemq.controller;

import org.springboot.samples.activemq.jms.MessageSender;
import org.springboot.samples.activemq.listener.ApplicationClosedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController implements ApplicationEventPublisherAware {

	@Autowired
	private MessageSender messageSender;

	private ApplicationEventPublisher eventPublisher;

	@RequestMapping("/stopApplication")
	public String stopApplication() throws Exception {
		eventPublisher.publishEvent(new ApplicationClosedEvent("CLOSE"));
		return "Stop";
	}

	@RequestMapping("/sendMessage")
	public String sendMessage() throws Exception {
		messageSender.send("jms.test.queue", "Hello");
		return "Hello";
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.eventPublisher = applicationEventPublisher;
	}
}
