package ru.flamexander.transfer.service.core.backend.configurations;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@NoArgsConstructor
@Data
@ConfigurationProperties("integrations.limits-service")
public class LimitsServiceProperties implements RestClientProperties {
    private String url;
    private Duration readTimeout;
    private Duration writeTimeout;
}
