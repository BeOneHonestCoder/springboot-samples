package org.springboot.samples.resilience4j.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.stereotype.Service;

@Service
public class Resilience4jService {

    /**
     * [Method Call]
     *      ↓
     * @CircuitBreaker → AOP → CircuitBreakerAspect
     *      ↓
     * circuitBreaker.executeCheckedSupplier(proceedingJoinPoint::proceed) -> CircuitBreakerStateMachine
     */
    @CircuitBreaker(name = "apiCircuitBreaker", fallbackMethod = "apiCircuitBreakerFallback")
    public String apiCircuitBreaker() {
        if (Math.random() > 0.5) {
            throw new IllegalStateException("Service down");
        }
        return "{\"status\":\"OK\"}";
    }

    private String apiCircuitBreakerFallback(Throwable t) {
        return """
            {
              "status": "DEGRADED",
              "message": "Remote Service unavailable"
            }
            """;
    }

    /**
     * [Method Call]
     *      ↓
     * @RateLimiter → AOP → RateLimiterAspect
     *      ↓
     * rateLimiter.executeCheckedSupplier(proceedingJoinPoint::proceed) -> AtomicRateLimiter
     */
    @RateLimiter(name = "apiRateLimiter", fallbackMethod = "apiRateLimiterFallback")
    public String apiRateLimiter() {
        return "Success: API called at " + System.currentTimeMillis();
    }

    private String apiRateLimiterFallback(Throwable t) {
        return "Too many requests! Please try again later.";
    }
}
