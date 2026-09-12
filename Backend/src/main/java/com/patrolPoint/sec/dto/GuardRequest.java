package com.patrolPoint.sec.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuardRequest {
    private String idNumber;
    private String phone;
}