package org.springboot.samples.client.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "feature")
public record FeatureProperties(
        boolean darkMode,
        boolean betaAccess
) {}
