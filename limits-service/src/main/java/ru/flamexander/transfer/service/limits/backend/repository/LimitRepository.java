package ru.flamexander.transfer.service.limits.backend.repository;

import ru.flamexander.transfer.service.limits.backend.entities.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findByClientId(Long clientId);

}
