package org.springboot.samples.es.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SayHelloController {

	@RequestMapping("/sayHello")
	public String sayHello() {
		return "Hello";
	}

}
