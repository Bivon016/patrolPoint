package com.patrolPoint.sec.service;

import com.patrolPoint.sec.dto.ClientRequest;
import com.patrolPoint.sec.dto.ClientResponse;
import com.patrolPoint.sec.model.Client;
import com.patrolPoint.sec.model.User;
import com.patrolPoint.sec.repository.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepo clientRepository;

    public ClientResponse create(ClientRequest request, User authenticatedUser) {
        if (clientRepository.existsByUserId(authenticatedUser.getId())) {
            throw new IllegalStateException(
                    "A client profile already exists for user " + authenticatedUser.getEmail());
        }

        Client client = Client.builder()
                .user(authenticatedUser)
                .companyName(request.getCompanyName())
                .contactDetails(request.getContactDetails())
                .address(request.getAddress())
                .build();

        clientRepository.save(client);
        return toResponse(client);
    }

    public List<ClientResponse> getAll() {
        return clientRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ClientResponse getById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No client found with id " + id));
        return toResponse(client);
    }

    public ClientResponse getByCompanyName(String companyName) {
        Client client = clientRepository.findByCompanyName(companyName)
                .orElseThrow(() -> new IllegalArgumentException("No client found with companyName " + companyName));
        return toResponse(client);
    }

    private ClientResponse toResponse(Client client) {
        return ClientResponse.builder()
                .id(client.getId())
                .userId(client.getUser().getId())
                .fullName(client.getUser().getFullName())
                .email(client.getUser().getEmail())
                .companyName(client.getCompanyName())
                .contactDetails(client.getContactDetails())
                .address(client.getAddress())
                .build();
    }
}