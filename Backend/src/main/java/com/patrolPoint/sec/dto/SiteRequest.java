package com.patrolPoint.sec.dto;

import com.patrolPoint.sec.model.Client;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SiteRequest {
    private String name;
    private Client clientId;
    private String description;

}
