package com.main.authservice.dto.permission;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionDto {
    private Long id;
    private String name;
    private String description;
    private String code;
    private Boolean isDeleted;
    private Boolean isActive;
    private Date createdAt;
    private Date lastUpdatedAt;
}
