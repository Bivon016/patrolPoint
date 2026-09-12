package com.patrolPoint.sec.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Guard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false, unique = true)
    private String idNumber;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GuardStatus status;

    private String currentPosting;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = GuardStatus.ACTIVE;
        }
    }
}