package com.company.training.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id="appInfo")
@RequiredArgsConstructor
public class ApplicationInfoActuator {
    private final Environment environment;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @ReadOperation
    public Map<String, String> getAppInfo() {
        String profiles = String.join(",", environment.getActiveProfiles());

        return Map.of(
                "PROFILE", profiles,
                "DB_URL", dbUrl
        );
    }
}
