package org.springboot.samples.gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/demo")
public class GatewayController {

	private Logger LOG = LoggerFactory.getLogger(GatewayController.class);

	@GetMapping("/search")
	@Retryable(value = {ArithmeticException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public Mono<String> sendMessage() throws Exception {
		LOG.info("hello");
		int i = 1/0;
		return Mono.just("hello");
	}

	@Retryable(value = {ArithmeticException.class}, maxAttempts = 3, backoff = @Backoff(delay = 1000))
	public void retryMethod() {
		LOG.info("Hi");
		int i = 1/0;
	}

	@Recover
	public Mono<String> recoverMethod(ArithmeticException exception){
		LOG.info("recover method");
		return Mono.just("hello");
	}


	@GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<ServerSentEvent<String>> streamEvents() {
		return Flux.interval(Duration.ofSeconds(1))
				.take(5)
				.map(seq -> ServerSentEvent.builder("Event " + seq).build())
				.concatWith(Flux.just(
						ServerSentEvent.builder("[END]").build()
				));
	}


}
