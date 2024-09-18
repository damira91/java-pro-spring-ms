package ru.flamexander.transfer.service.core.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.transfer.service.core.api.dtos.ExecuteTransferDtoRequest;
import ru.flamexander.transfer.service.core.backend.dtos.TransferDto;
import ru.flamexander.transfer.service.core.backend.services.TransfersService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transfers")
public class TransfersController {
    private final TransfersService transfersService;

    @PostMapping("/execute")
    public void executeTransfer(
            @RequestHeader Long clientId,
            @RequestBody ExecuteTransferDtoRequest executeTransferDtoRequest
    ) {
        transfersService.execute(clientId, executeTransferDtoRequest);
    }

    @GetMapping
    public List<TransferDto> getTransfersHistory(
            @RequestHeader Long clientId
    ) {
        return transfersService.getTransfersHistory(clientId);
    }
}
