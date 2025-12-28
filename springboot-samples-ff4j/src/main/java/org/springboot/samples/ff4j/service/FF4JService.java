package org.springboot.samples.ff4j.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.property.Property;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FF4JService {

    private final FF4j ff4j;

    public void testFF4J() {
        boolean testFlag = ff4j.check("Test");
        log.info("Test FF4J enabled or not: {}", testFlag);
        Feature testFeature = ff4j.getFeature("Test");
        log.info("Test FF4J Feature: {}", testFeature);
        Property<?> testProperty =  ff4j.getProperty("TestProperty");
        log.info("Test FF4J Property: {}", testProperty);
    }

}
