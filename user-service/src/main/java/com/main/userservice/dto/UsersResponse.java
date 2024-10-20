package com.main.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsersResponse {
    private UUID id;
    private String fullName;
    private String avatar;
    private Date dob;
    private String phone;
    private String phonePrefix;
    private String email;
    private String gender;
    private String address;
    private Boolean isDeleted;
    private Boolean isActive;
    private String searchInfo;
    private Date createdAt;
    private String createdBy;
    private Date lastUpdatedAt;
    private String lastUpdatedBy;
}
