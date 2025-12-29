package org.springboot.samples.wiremock.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.Extension;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WireMockServerConfig {

    @Bean(destroyMethod = "stop")
    public WireMockServer wireMockServer(List<Extension> extensions) {
        WireMockServer server = new WireMockServer(WireMockConfiguration.options()
                .port(8088)
                .stubCorsEnabled(true)
                .notifier(new ConsoleNotifier(true))
                .usingFilesUnderClasspath("wiremock")
                .extensions(extensions.toArray(new Extension[0]))
        );

        server.start();
        return server;
    }
}
