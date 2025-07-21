package org.springboot.samples.ws.config;

import org.springboot.samples.ws.handler.ChatHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSocketConfig {

    @Bean
    public HandlerMapping handlerMapping() {
        Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/chat", chatHandler());
        int order = -1;

        return new SimpleUrlHandlerMapping(map, order);
    }

    @Bean
    public ChatHandler chatHandler() {
        return new ChatHandler();
    }
}
