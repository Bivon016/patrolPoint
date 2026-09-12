package com.patrolPoint.sec.repository;

import com.patrolPoint.sec.model.Client;
import com.patrolPoint.sec.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    boolean existsByUserId(Long id);
    Optional<Client> findByCompanyName(String companyName);


}
