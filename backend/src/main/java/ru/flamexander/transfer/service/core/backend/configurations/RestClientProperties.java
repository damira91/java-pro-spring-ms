package ru.flamexander.transfer.service.core.backend.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;


public interface RestClientProperties {
    String getUrl();
    Duration getReadTimeout();
    Duration getWriteTimeout();
}
