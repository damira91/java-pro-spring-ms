package ru.flamexander.transfer.service.core.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "client_id_from")
    private Long clientIdFrom;

    @Column(name = "client_id_to")
    private Long clientIdTo;

    @Column(name = "account_number_from")
    private String accountNumberFrom;

    @Column(name = "account_number_to")
    private String accountNumberTo;

    @Column(name = "transfer_sum")
    private BigDecimal transferSum;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Transfer(Long clientIdFrom, Long clientIdTo, String accountNumberFrom, String accountNumberTo, BigDecimal transferSum, String status) {
        this.clientIdFrom = clientIdFrom;
        this.clientIdTo = clientIdTo;
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.transferSum = transferSum;
        this.status = status;
    }
}