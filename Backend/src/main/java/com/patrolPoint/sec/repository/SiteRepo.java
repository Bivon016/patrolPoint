package com.patrolPoint.sec.repository;

import com.patrolPoint.sec.model.Client;
import com.patrolPoint.sec.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SiteRepo extends JpaRepository<Site, Long> {
    Optional<Site> findByClientId(Long clientId);

}
