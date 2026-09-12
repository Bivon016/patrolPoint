package com.patrolPoint.sec.controller;

import com.patrolPoint.sec.dto.SiteRequest;
import com.patrolPoint.sec.dto.SiteResponse;
import com.patrolPoint.sec.model.User;
import com.patrolPoint.sec.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/site")
@RequiredArgsConstructor
public class SiteController {
    private final SiteService siteService;

    @PostMapping
    public ResponseEntity<SiteResponse> createSite(@RequestBody SiteRequest request,@AuthenticationPrincipal User authenticatedUser) throws AccessDeniedException {
        return ResponseEntity.ok(siteService.createSite(request,authenticatedUser));

    }
    @PutMapping("/update")
    public ResponseEntity<SiteResponse> updateSite(@RequestBody SiteRequest request,@AuthenticationPrincipal User authenticatedUser) throws AccessDeniedException {
        return ResponseEntity.ok(siteService.updateSite(request,authenticatedUser));
    }

    @GetMapping
    public ResponseEntity<List<SiteResponse>> getAllSites(@AuthenticationPrincipal User authenticatedUser) throws AccessDeniedException {
        return ResponseEntity.ok(siteService.getAllSites(authenticatedUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteResponse> getSiteById(@RequestParam Long id,@AuthenticationPrincipal User authenticatedUser) throws AccessDeniedException {
        return ResponseEntity.ok(siteService.getSiteById(authenticatedUser,id));
    }
    @GetMapping("/{Id}")
    public ResponseEntity<SiteResponse> getSiteByClient(Long clientId,@AuthenticationPrincipal User authenticatedUser) throws AccessDeniedException {
        return ResponseEntity.ok(siteService.getSiteByClientId(authenticatedUser,clientId));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSite(@AuthenticationPrincipal User authenticatedUser,Long id) throws AccessDeniedException {
        siteService.deleteSite(authenticatedUser,id);
        return ResponseEntity.noContent().build();
    }
}
