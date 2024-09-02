package ru.flamexander.transfer.service.limits.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.transfer.service.core.api.dtos.LimitDtoRequest;
import ru.flamexander.transfer.service.core.api.dtos.LimitDtoResponse;
import ru.flamexander.transfer.service.limits.backend.entities.Limit;
import ru.flamexander.transfer.service.limits.backend.services.LimitService;

import java.util.function.Function;

@RestController
@RequestMapping("/api/v1/limits")
@RequiredArgsConstructor
public class LimitController {
    private final LimitService limitService;
    private Function<Limit, LimitDtoResponse> entityToDto = limit -> new LimitDtoResponse(limit.getClientId(),limit.getRemainingLimit(), true);

    @GetMapping("/{id}")
    public LimitDtoResponse getClientLimit(@PathVariable Long id){
        return entityToDto.apply(limitService.getOrCreateLimit(id));
    }
    @PostMapping("/debit")
    public LimitDtoResponse debitClientLimit(@RequestHeader Long clientId,
                                             @RequestBody LimitDtoRequest limitRequest){
        return entityToDto.apply(limitService.debitLimit(clientId, limitRequest.getLimit()));
    }
    @PostMapping("/rollback")
    public void rollbackClientLimit(@RequestHeader Long clientId,
                                    @RequestBody LimitDtoRequest limitRequest) {

        limitService.rollbackLimit(clientId, limitRequest.getLimit());
    }
}
