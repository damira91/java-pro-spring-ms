package ru.flamexander.transfer.service.core.backend.dtos;

import java.math.BigDecimal;

public interface TransferDto {
    Long getClientIdFrom();
    Long getClientIdTo();
    String getAccountNumberFrom();
    String getAccountNumberTo();
    BigDecimal getTransferSum();
    String getStatus();
}
