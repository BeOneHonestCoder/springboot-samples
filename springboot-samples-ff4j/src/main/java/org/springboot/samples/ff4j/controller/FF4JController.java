package org.springboot.samples.ff4j.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springboot.samples.ff4j.service.FF4JService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FF4JController {

    private final FF4JService ff4JService;

    @GetMapping("/testFF4J")
    public String testFF4J() {
        ff4JService.testFF4J();
        return "Hello FF4J";
    }

}
