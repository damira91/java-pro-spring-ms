package ru.flamexander.transfer.service.clients.backend.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.flamexander.transfer.service.clients.backend.dtos.ClientInfoResponse;
import ru.flamexander.transfer.service.clients.backend.entities.Client;
import ru.flamexander.transfer.service.clients.backend.services.ClientsService;

@RestController
@RequestMapping("/api/v1/clients")

public class ClientsController {
    private final ClientsService clientsService;

    @Autowired
    public ClientsController(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/{id}")
    public ClientInfoResponse getClientInfo(@PathVariable Long id) {
        Client client = clientsService.getClientInfoById(id);
        return new ClientInfoResponse(client.getId(), client.getFirstName(), client.getMiddleName(), client.getLastName(), "ACTIVE");
    }
}
