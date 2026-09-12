package com.patrolPoint.sec.dto;

import com.patrolPoint.sec.model.Client;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SiteResponse {

    private Long id;
    private Client clientId;
    private String name;
    private String description;

}
