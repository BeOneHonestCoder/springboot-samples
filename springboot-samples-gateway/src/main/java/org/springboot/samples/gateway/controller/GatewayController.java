package org.springboot.samples.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo")
public class GatewayController {

	@GetMapping("/search")
	public Mono<String> sendMessage() throws Exception {

		return Mono.just("hello");
	}

}
