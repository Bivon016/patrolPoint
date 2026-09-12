package com.patrolPoint.sec.repository;

import com.patrolPoint.sec.model.Guard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuardRepo extends JpaRepository<Guard,Long> {
    boolean existsByUserId(Long id);
    Optional<Guard> findByIdNumber(String idNumber);
    Optional<Guard> findByUserId(Long id);

}
