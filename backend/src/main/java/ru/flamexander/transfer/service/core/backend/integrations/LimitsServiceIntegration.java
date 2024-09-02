package ru.flamexander.transfer.service.core.backend.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.flamexander.transfer.service.core.api.dtos.LimitDtoRequest;
import ru.flamexander.transfer.service.core.api.dtos.LimitDtoResponse;
import ru.flamexander.transfer.service.limits.backend.errors.AppLogicException;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class LimitsServiceIntegration {
    private final static String debitLimitUrl = "/debit";
    private final static String rollbackLimitUrl = "/rollback";
    private final RestTemplate restTemplate;

    @Value("${integrations.limits-service.url}")
    private String limitServiceUrl;

    public LimitDtoResponse debitLimit(Long clientId, BigDecimal amount) {
        LimitDtoRequest request = new LimitDtoRequest(clientId, amount);

        try {
            ResponseEntity<LimitDtoResponse> response = restTemplate.postForEntity(
                    limitServiceUrl + debitLimitUrl,
                    request,
                    LimitDtoResponse.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                throw new AppLogicException("DEBIT_LIMIT_FAILED", "Не удалось провести дебетирование лимита. Статус: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException e) {
            throw new AppLogicException("DEBIT_LIMIT_ERROR", "Ошибка при дебетировании лимита: " + e.getMessage());
        }
    }

    public void rollbackLimit(Long clientId, BigDecimal amount) {
        LimitDtoRequest request = new LimitDtoRequest(clientId, amount);

        try {
            ResponseEntity<Void> response = restTemplate.postForEntity(
                    limitServiceUrl + rollbackLimitUrl,
                    request,
                    Void.class
            );

            if (response.getStatusCode() != HttpStatus.NO_CONTENT) { // Проверяем, что статус ответа соответствует ожидаемому
                throw new AppLogicException("ROLLBACK_LIMIT_FAILED", "Не удалось выполнить откат лимита. Статус: " + response.getStatusCode());
            }
        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException e) {
            throw new AppLogicException("ROLLBACK_LIMIT_ERROR", "Ошибка при откате лимита: " + e.getMessage());
        }
    }
}
