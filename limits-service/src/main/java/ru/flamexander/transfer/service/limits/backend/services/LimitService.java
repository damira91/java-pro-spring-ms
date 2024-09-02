package ru.flamexander.transfer.service.limits.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.flamexander.transfer.service.limits.backend.entities.Limit;
import ru.flamexander.transfer.service.limits.backend.errors.AppLogicException;
import ru.flamexander.transfer.service.limits.backend.errors.ResourceNotFoundException;
import ru.flamexander.transfer.service.limits.backend.repository.LimitRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LimitService {
    private final LimitRepository limitRepository;
    @Value("${application.limits.default_limit}")
    private BigDecimal defaultLimitValue;

    @Transactional
    public Limit debitLimit(Long userId, BigDecimal amount) {
        Limit limit = getOrCreateLimit(userId);
        if (limit.getRemainingLimit().compareTo(amount) < 0) {
            throw new AppLogicException("INSUFFICIENT_LIMIT", "Недостаточно лимита для выполнения операции");
        }
        limit.setRemainingLimit(limit.getRemainingLimit().subtract(amount));
        return limitRepository.save(limit);
    }
    @Transactional
    public void rollbackLimit(Long clientId, BigDecimal amount) {
        Limit limit = getOrCreateLimit(clientId);
        limit.setRemainingLimit(limit.getRemainingLimit().add(amount));
        limitRepository.save(limit);
    }
    public Limit getOrCreateLimit(Long clientId) {
        if (!limitRepository.existsById(clientId)) {
            Limit newLimit = new Limit(clientId, defaultLimitValue);
            return limitRepository.save(newLimit);
        }
        return limitRepository.findByClientId(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("CLIENT_NOT_FOUND", "Клиент с id " + clientId + " не найден."));
    }
    @Scheduled(cron = "0 0 0 * * ?")
    public void resetAllLimitsToDefault() {
        List<Limit> allLimits = limitRepository.findAll();
        allLimits.forEach(limit -> limit.setRemainingLimit(defaultLimitValue));
        limitRepository.saveAll(allLimits);
    }
}
