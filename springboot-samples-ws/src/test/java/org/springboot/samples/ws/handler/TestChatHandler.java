package org.springboot.samples.ws.handler;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@SpringBootTest
public class TestChatHandler {

    @Test
    public void testChatHandler() {
        WebSocketClient client = new ReactorNettyWebSocketClient();

        client.execute(
                URI.create("ws://localhost:8085/chat"),
                session -> {

                    Mono<Void> sendInput = Flux.just("Hello", "World")
                            .doOnNext(msg -> System.out.println("Client sending payload: " + msg))
                            .map(session::textMessage)
                            .as(session::send);

                    Flux<String> receiveFlow = session.receive()
                            .map(WebSocketMessage::getPayloadAsText)
                            .doOnNext(msg ->
                                    System.out.println("Client received: " + msg)
                            );

                    return sendInput.thenMany(receiveFlow).then();
                }
        ).block(Duration.ofSeconds(30));
    }

}
