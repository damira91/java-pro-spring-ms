package ru.flamexander.transfer.service.core.backend.integrations;

import ru.flamexander.transfer.service.core.backend.dtos.ClientInfoResponseDto;

public interface ClientsInfoServiceIntegration {
    ClientInfoResponseDto getClientInfo(Long id);
}
