package com.patrolPoint.sec.service;

import com.patrolPoint.sec.dto.SiteRequest;
import com.patrolPoint.sec.dto.SiteResponse;
import com.patrolPoint.sec.model.Role;
import com.patrolPoint.sec.model.Site;
import com.patrolPoint.sec.model.User;
import com.patrolPoint.sec.repository.SiteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SiteService {

    private final SiteRepo siteRepo;

    public SiteResponse createSite(SiteRequest request,User authenticatedUser) throws AccessDeniedException {
        if(authenticatedUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("You cannot create a site you dont have the permissions!!");
        }

        Site site = Site.builder()
                .name(request.getName())
                .client(request.getClientId())
                .description(request.getDescription())
                .build();

        siteRepo.save(site);
        return toResponse(site);
    }

    public void deleteSite(User authenticatedUser,Long id) throws AccessDeniedException {
        if(authenticatedUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("You cannot delete a site you dont have the permissions!!");
        }


        Site site = siteRepo.findById(id)
                .orElseThrow(()->new IllegalStateException("No site with that id found !!!"));

        siteRepo.delete(site);
    }

    public SiteResponse updateSite(SiteRequest request,User authenticatedUser) throws AccessDeniedException {
        if(authenticatedUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("You cannot update a site you dont have the permissions!!");
        }
        Site site = Site.builder()
                .name(request.getName())
                .client(request.getClientId())
                .description(request.getDescription())
                .build();
        siteRepo.save(site);
        return toResponse(site);
    }

    public List<SiteResponse> getAllSites(User authenticatedUser) throws AccessDeniedException {
        if(authenticatedUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("You cannot get all sites you dont have the permissions!");
        }
        List<Site> sites = siteRepo.findAll();
        return sites
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public SiteResponse getSiteById(User authenticatedUser,Long id) throws AccessDeniedException {
        if(authenticatedUser.getRole() != Role.ADMIN) {
            throw new AccessDeniedException("You cannot get a site you dont have the permissions!!");
        }
        Site site = siteRepo.findById(id).orElseThrow(()->new IllegalStateException("No site with that id found !!!"));
        return toResponse(site);
    }

    public SiteResponse getSiteByClientId(
            User authenticatedUser,
            Long clientId
    ) throws AccessDeniedException {

        if (authenticatedUser.getRole() == Role.CLIENT &&
                !authenticatedUser.getId().equals(clientId)) {

            throw new AccessDeniedException(
                    "You cannot access this client's site."
            );
        }

        if (authenticatedUser.getRole() != Role.CLIENT &&
                authenticatedUser.getRole() != Role.ADMIN) {

            throw new AccessDeniedException(
                    "You cannot get a site by that id."
            );
        }

        Site site = siteRepo.findByClientId(clientId)
                .orElseThrow(() ->  new AccessDeniedException(
                "You cannot access this client's site."
        ));


        return toResponse(site);
    }

    private SiteResponse toResponse(Site site) {
        return SiteResponse.builder()
                .id(site.getId())
                .name(site.getName())
                .clientId(site.getClient())
                .description(site.getDescription())
                .build();
    }
}
