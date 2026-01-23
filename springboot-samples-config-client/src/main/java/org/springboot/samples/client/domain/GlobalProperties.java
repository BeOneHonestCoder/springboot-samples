package org.springboot.samples.client.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "global")
public record GlobalProperties(
        String company,
        String platformVersion
) {}
