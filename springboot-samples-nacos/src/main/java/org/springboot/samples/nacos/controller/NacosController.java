package org.springboot.samples.nacos.controller;

import com.alibaba.cloud.nacos.annotation.NacosConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosController {


    /**
     * NacosConfigAutoConfiguration/NacosAnnotationProcessor/NacosConfigManager/ConfigService
     */
    @NacosConfig(dataId = "springboot-samples-nacos", group = "DEFAULT_GROUP", key = "app.message")
    private String message;

    @NacosConfig(dataId = "springboot-samples-nacos", group = "DEFAULT_GROUP", key = "rate")
    private String rate;

    @RequestMapping(value = "/sayHelloWorld", method = RequestMethod.GET)
    public String sayHelloWorld() {
        return "Hello" + message + rate;
    }
}
