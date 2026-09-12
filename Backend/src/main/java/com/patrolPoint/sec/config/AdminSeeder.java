package com.patrolPoint.sec.config;

import com.patrolPoint.sec.model.Role;
import com.patrolPoint.sec.model.User;
import com.patrolPoint.sec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.admin.default-email}")
    private String defaultAdminEmail;

    @Value("${app.admin.default-password}")
    private String defaultAdminPassword;

    @Override
    public void run(String... args) {
        if (userRepository.existsByRole(Role.ADMIN)) {
            log.info("Admin user already exists — skipping seed.");
            return;
        }

        User admin = User.builder()
                .fullName("Default Admin")
                .email(defaultAdminEmail)
                .passwordHash(passwordEncoder.encode(defaultAdminPassword))
                .role(Role.ADMIN)
                .build();

        userRepository.save(admin);
        log.info("Seeded default admin user: {}", defaultAdminEmail);
    }
}