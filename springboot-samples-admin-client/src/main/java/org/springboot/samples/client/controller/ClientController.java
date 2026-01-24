package org.springboot.samples.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ClientController {

    @GetMapping("/test/final-client")
    public Map<String, Object> showClient() {
        Map<String, Object> result = new HashMap<>();

        result.put("1_global_company", "client");

        return result;
    }
}
