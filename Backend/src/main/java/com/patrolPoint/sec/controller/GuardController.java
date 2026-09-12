package com.patrolPoint.sec.controller;

import com.patrolPoint.sec.dto.GuardRequest;
import com.patrolPoint.sec.dto.GuardResponse;
import com.patrolPoint.sec.model.User;
import com.patrolPoint.sec.service.GuardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guards")
@RequiredArgsConstructor
public class GuardController {

    private final GuardService guardService;

    @PostMapping
    public ResponseEntity<GuardResponse> create(
            @RequestBody GuardRequest request,
            @AuthenticationPrincipal User authenticatedUser
    ) {
        return ResponseEntity.ok(guardService.createGuard(request, authenticatedUser));
    }

    @GetMapping
    public ResponseEntity<List<GuardResponse>> getAll() {
        return ResponseEntity.ok(guardService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuardResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(guardService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<GuardResponse> getByIdNumber(@RequestParam String idNumber) {
        return ResponseEntity.ok(guardService.getByIdNumber(idNumber));
    }

    @PutMapping("/me")
    public ResponseEntity<GuardResponse> updateOwnProfile(
            @RequestBody GuardRequest request,
            @AuthenticationPrincipal User authenticatedUser
    ) {
        return ResponseEntity.ok(guardService.updateOwnProfile(request, authenticatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id,
            @AuthenticationPrincipal User authenticatedUser
    ) {
        guardService.deleteById(id, authenticatedUser);
        return ResponseEntity.noContent().build();
    }
}