package org.springboot.samples.nacos.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "springboot-samples-nacos")
public interface HelloService {

    @RequestMapping(value = "/sayHelloWorld", method = RequestMethod.GET)
    String sayHelloWorld();
}
