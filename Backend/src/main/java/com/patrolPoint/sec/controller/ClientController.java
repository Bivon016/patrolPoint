package com.patrolPoint.sec.controller;

import com.patrolPoint.sec.dto.ClientRequest;
import com.patrolPoint.sec.dto.ClientResponse;
import com.patrolPoint.sec.model.User;
import com.patrolPoint.sec.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponse> create(
            @RequestBody ClientRequest request,
            @AuthenticationPrincipal User authenticatedUser
    ) {
        return ResponseEntity.ok(clientService.create(request, authenticatedUser));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<ClientResponse> getByCompanyName(@RequestParam String companyName) {
        return ResponseEntity.ok(clientService.getByCompanyName(companyName));
    }
}