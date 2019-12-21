package org.springboot.samples.activemq.controller;

import org.springboot.samples.activemq.jms.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

	@Autowired
	private MessageSender messageSender;

	@RequestMapping("/sendMessage")
	public String sendMessage() throws Exception {
		messageSender.send("jms.test.queue", "Hello");

		return "Hello";
	}

}
