package com.patrolPoint.sec.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientResponse {
    private Long id;
    private Long userId;
    private String fullName;
    private String email;
    private String companyName;
    private String contactDetails;
    private String address;
}