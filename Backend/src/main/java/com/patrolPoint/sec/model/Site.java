package com.patrolPoint.sec.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "client_id" , nullable = false)
    private Client client;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    private String description;

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }
}
