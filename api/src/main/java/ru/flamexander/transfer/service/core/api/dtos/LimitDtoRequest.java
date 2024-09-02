package ru.flamexander.transfer.service.core.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LimitDtoRequest {
    private Long clientId;
    private BigDecimal limit;

}
