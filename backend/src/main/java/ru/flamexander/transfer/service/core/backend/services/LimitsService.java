package ru.flamexander.transfer.service.core.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.flamexander.transfer.service.core.backend.integrations.LimitsServiceIntegration;
import ru.flamexander.transfer.service.core.api.dtos.LimitDtoRequest;
import ru.flamexander.transfer.service.core.api.dtos.LimitDtoResponse;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class LimitsService {
    private final LimitsServiceIntegration limitsServiceIntegration;


    public boolean hasSufficientLimit(Long clientId, BigDecimal amount){
        LimitDtoRequest limitDtoRequest = new LimitDtoRequest(clientId, amount);
        LimitDtoResponse response = limitsServiceIntegration.debitLimit(clientId, amount);
        return response != null && response.isHasLimit();
    }

    public void rollbackLimit(Long clientId, BigDecimal amount) {
        limitsServiceIntegration.rollbackLimit(clientId, amount);
    }
}
