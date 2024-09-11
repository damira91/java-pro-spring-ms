package ru.flamexander.transfer.service.core.backend.services;

import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.backend.dtos.TransferDto;

import java.util.List;

public interface TransfersService {
    void execute(Long clientId, ExecuteTransferDtoRequest executeTransferDtoRequest);
    List<TransferDto> getTransfersHistory(Long clientId);
}
