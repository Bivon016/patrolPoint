package com.patrolPoint.sec.dto;

import com.patrolPoint.sec.model.GuardStatus;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardResponse {
    private Long id;
    private Long userId;
    private String fullName;
    private String email;
    private String idNumber;
    private String phone;
    private GuardStatus status;
    private String currentPosting;
}