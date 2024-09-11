package ru.flamexander.transfer.service.core.backend.configurations;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@NoArgsConstructor
@Data
@ConfigurationProperties("integrations.clients-info-service")
public class ClientsInfoServiceProperties implements RestClientProperties {
    private String url;
    private Duration readTimeout;
    private Duration writeTimeout;
}