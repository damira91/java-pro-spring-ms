package ru.flamexander.transfer.service.core.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.flamexander.transfer.service.core.backend.dtos.TransferDto;
import ru.flamexander.transfer.service.core.backend.entities.Transfer;

import java.util.List;

@Repository
public interface TransfersRepository  extends JpaRepository<Transfer, Long> {
    @Query("select t from Transfer t where t.clientIdFrom = :clientId or t.clientIdTo = :clientId order by t.createdAt desc")
    List<TransferDto> findAllClientTransfers(Long clientId);
}
