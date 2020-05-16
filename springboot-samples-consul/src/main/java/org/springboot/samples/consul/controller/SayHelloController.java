package org.springboot.samples.consul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping("/sayHello")
	public String sayHello() {
		return "Hello";
	}

}
