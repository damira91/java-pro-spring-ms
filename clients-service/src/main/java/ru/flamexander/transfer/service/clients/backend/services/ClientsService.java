package ru.flamexander.transfer.service.clients.backend.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.flamexander.transfer.service.clients.backend.entities.Client;
import ru.flamexander.transfer.service.clients.backend.errors.ResourceNotFoundException;
import ru.flamexander.transfer.service.clients.backend.repository.ClientsRepository;

@Service

public class ClientsService {
    private final ClientsRepository clientsRepository;

    @Autowired
    public ClientsService(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public Client getClientInfoById(Long id) {
        return clientsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Клиент с id = " + id + " не найден"));
    }
}
