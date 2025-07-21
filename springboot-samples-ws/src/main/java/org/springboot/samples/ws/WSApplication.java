package org.springboot.samples.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class WSApplication {

    public static void main(String[] args) {
        SpringApplication.run(WSApplication.class, args);
    }
}
