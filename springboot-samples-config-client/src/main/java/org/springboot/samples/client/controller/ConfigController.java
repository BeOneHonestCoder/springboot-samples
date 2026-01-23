package org.springboot.samples.client.controller;

import lombok.RequiredArgsConstructor;
import org.springboot.samples.client.domain.AppProperties;
import org.springboot.samples.client.domain.FeatureProperties;
import org.springboot.samples.client.domain.GlobalProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ConfigController {

    private final GlobalProperties global;
    private final AppProperties app;
    private final FeatureProperties feature;

    @GetMapping("/test/final-config")
    public Map<String, Object> showConfig() {
        Map<String, Object> result = new HashMap<>();

        result.put("1_global_company", global.company());
        result.put("1_global_version", global.platformVersion());

        result.put("2_app_envName", app.envName());
        result.put("2_app_desc", app.description());

        result.put("3_feature_darkMode", feature.darkMode());
        result.put("3_feature_betaAccess", feature.betaAccess());

        return result;
    }
}
