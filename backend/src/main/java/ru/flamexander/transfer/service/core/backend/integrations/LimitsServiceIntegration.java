package ru.flamexander.transfer.service.core.backend.integrations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.flamexander.transfer.service.core.api.dtos.LimitDtoRequest;
import ru.flamexander.transfer.service.core.api.dtos.LimitDtoResponse;
import ru.flamexander.transfer.service.core.backend.errors.AppLogicException;

@Component
@ConditionalOnMissingBean(RestTemplate.class)
public class LimitsServiceIntegration {
    private static final String DEBIT_LIMIT_URL = "/debit";
    private static final String ROLLBACK_LIMIT_URL = "/rollback";
    private final RestClient limitsInfoClient;

    @Autowired
    public LimitsServiceIntegration(@Qualifier("limitsClient") RestClient limitsInfoClient) {
        this.limitsInfoClient = limitsInfoClient;
    }

    public LimitDtoResponse getLimit(Long clientId) {
        return limitsInfoClient.get()
                .uri("/{id}", clientId)
                .retrieve()
                .onStatus(status -> status == HttpStatus.NOT_FOUND, (request, response) -> {
                    throw new AppLogicException("CLIENT_NOT_FOUND", "Клиент с id " + clientId + " не найден.");
                })
                .body(LimitDtoResponse.class);
    }

    public LimitDtoResponse debitLimit(LimitDtoRequest limitRequest, Long clientId) {
        return limitsInfoClient.post()
                .uri("/{api}",DEBIT_LIMIT_URL)
                .header("clientId", clientId.toString())
                .body(limitRequest)
                .retrieve()
                .body(LimitDtoResponse.class);
    }
    public void rollbackLimit(LimitDtoRequest limitRequest, Long clientId){
        limitsInfoClient.post()
                .uri("/{api}", ROLLBACK_LIMIT_URL)
                .header("clientId", clientId.toString())
                .body(limitRequest)
                .retrieve()
                .body(LimitDtoResponse.class);
    }

}
