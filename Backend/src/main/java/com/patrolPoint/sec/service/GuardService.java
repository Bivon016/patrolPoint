package com.patrolPoint.sec.service;

import com.patrolPoint.sec.dto.GuardRequest;
import com.patrolPoint.sec.dto.GuardResponse;
import com.patrolPoint.sec.model.Guard;
import com.patrolPoint.sec.model.GuardStatus;
import com.patrolPoint.sec.model.Role;
import com.patrolPoint.sec.model.User;
import com.patrolPoint.sec.repository.GuardRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuardService {

    private final GuardRepo guardRepo;

    public GuardResponse createGuard(GuardRequest request, User authenticatedUser) {
        if (guardRepo.existsByUserId(authenticatedUser.getId())) {
            throw new IllegalStateException(
                    "A guard profile already exists for user " + authenticatedUser.getEmail());
        }

        Guard guard = Guard.builder()
                .user(authenticatedUser)
                .idNumber(request.getIdNumber())
                .phone(request.getPhone())
                .status(GuardStatus.ACTIVE)
                .build();

        guardRepo.save(guard);
        return toResponse(guard);
    }

    public List<GuardResponse> getAll() {
        return guardRepo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public GuardResponse getById(Long id) {
        Guard guard = guardRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No guard found with id " + id));
        return toResponse(guard);
    }

    public GuardResponse getByIdNumber(String idNumber) {
        Guard guard = guardRepo.findByIdNumber(idNumber)
                .orElseThrow(() -> new IllegalArgumentException("No guard found with idNumber " + idNumber));
        return toResponse(guard);
    }

    // Guard updates their OWN contact info only — never idNumber, status, or posting.
    public GuardResponse updateOwnProfile(GuardRequest request, User authenticatedUser) {
        Guard guard = guardRepo.findByUserId(authenticatedUser.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "No guard profile exists yet for user " + authenticatedUser.getEmail()));

        guard.setPhone(request.getPhone());

        guardRepo.save(guard);
        return toResponse(guard);
    }

    public void deleteById(Long id, User authenticatedUser) {
        if (authenticatedUser.getRole() != Role.ADMIN) {
            throw new IllegalStateException("Only an Admin can delete a guard profile");
        }
        Guard guard = guardRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No guard found with id " + id));
        guardRepo.delete(guard);
    }

    private GuardResponse toResponse(Guard guard) {
        return GuardResponse.builder()
                .id(guard.getId())
                .userId(guard.getUser().getId())
                .fullName(guard.getUser().getFullName())
                .email(guard.getUser().getEmail())
                .idNumber(guard.getIdNumber())
                .phone(guard.getPhone())
                .status(guard.getStatus())
                .currentPosting(guard.getCurrentPosting())
                .build();
    }
}