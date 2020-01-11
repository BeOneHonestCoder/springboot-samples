package org.springboot.samples.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry()
public class RetryConfig {
}
