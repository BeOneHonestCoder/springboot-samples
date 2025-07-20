package org.springboot.samples.gateway.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class TestGatewayController {

    @Test
    public void testStream() {
        System.err.println("Stream");

        WebClient client = WebClient.create();

        client.get()
                .uri("http://localhost:8084/demo/stream")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .doOnNext(data -> System.out.println("Received: " + data))
                .doOnComplete(() -> System.out.println("Connection closed"))
                .blockLast();
    }

}
