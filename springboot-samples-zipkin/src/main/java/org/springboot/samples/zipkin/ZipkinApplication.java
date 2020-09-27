package org.springboot.samples.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import zipkin.server.ZipkinServer;
import zipkin2.server.internal.EnableZipkinServer;
import zipkin2.server.internal.RegisterZipkinHealthIndicators;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinApplication {
    //\io\zipkin\java\zipkin-server\2.12.9\zipkin-server-2.12.9.jar!\zipkin\server\ZipkinServer.class
    public static void main(String[] args) {
        new SpringApplicationBuilder(ZipkinServer.class)
                .listeners(new RegisterZipkinHealthIndicators())
                .properties("spring.config.name=" + getConfigName()).run(args);
    }

    private static String getConfigName() {
        return Stream.of("zipkin-server-shared", "application").collect(Collectors.joining(","));
    }
}
