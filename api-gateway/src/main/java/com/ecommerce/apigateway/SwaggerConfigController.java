package com.ecommerce.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SwaggerConfigController {

    @GetMapping("/v3/api-docs/swagger-config.json")
    public Map<String, Object> swaggerConfig() {
        Map<String, Object> config = new HashMap<>();
        List<Map<String, String>> urls = new ArrayList<>();

        urls.add(createUrl("order-service", "http://localhost:8080/v3/api-docs"));
        urls.add(createUrl("client-service", "http://localhost:8081/v3/api-docs"));
        urls.add(createUrl("inventory-service", "http://localhost:8082/v3/api-docs"));
        urls.add(createUrl("payment-service", "http://localhost:8083/v3/api-docs"));
        urls.add(createUrl("shipping-service", "http://localhost:8084/v3/api-docs"));

        config.put("urls", urls);
        return config;
    }

    private Map<String, String> createUrl(String name, String url) {
        Map<String, String> urlInfo = new HashMap<>();
        urlInfo.put("name", name);
        urlInfo.put("url", url);
        return urlInfo;
    }
}
