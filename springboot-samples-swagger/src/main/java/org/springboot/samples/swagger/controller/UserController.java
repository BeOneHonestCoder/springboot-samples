package org.springboot.samples.swagger.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping("/sayHello")
    public String sayHello() {
        return "Hello";
    }
}
