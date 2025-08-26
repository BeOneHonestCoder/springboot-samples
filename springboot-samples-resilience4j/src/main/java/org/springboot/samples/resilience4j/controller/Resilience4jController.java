package org.springboot.samples.resilience4j.controller;

import org.springboot.samples.resilience4j.service.Resilience4jService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Resilience4jController {

    @Autowired
    private Resilience4jService service;

    @RequestMapping(value = "/apiCircuitBreaker", method = RequestMethod.GET)
    public String apiCircuitBreaker() {
        return service.apiCircuitBreaker();
    }

    @RequestMapping(value = "/apiRateLimiter", method = RequestMethod.GET)
    public String apiRateLimiter() {
        return service.apiRateLimiter();
    }

}
