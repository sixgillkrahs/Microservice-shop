package com.main.userservice.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
