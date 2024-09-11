package ru.flamexander.transfer.service.core.backend.configurations;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@NoArgsConstructor
@Data
@ConfigurationProperties("app.transfers")
public class AppProperties {
    private boolean aiTransfersChecking;
    private List<String> blockedAccountsMasks;
    private int maxTransfersRetryCount;
    private ParamABC param1;
    private ParamABC param2;
}