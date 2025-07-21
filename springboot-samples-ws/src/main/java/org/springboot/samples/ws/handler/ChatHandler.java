package org.springboot.samples.ws.handler;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ChatHandler implements WebSocketHandler {

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        Flux<WebSocketMessage> output = session.receive()
                .doOnNext(msg -> System.out.println("Server Received: " + msg.getPayloadAsText()))
                .map(msg -> session.textMessage("Server ECHO: " + msg.getPayloadAsText()));

        return session.send(output);
    }

}
