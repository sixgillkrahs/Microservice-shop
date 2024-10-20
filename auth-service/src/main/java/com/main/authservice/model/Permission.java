package com.main.authservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Table(name = "permission")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String code;
    private Boolean isDeleted;
    private Boolean isActive;
    private Date createdAt;
    private Date lastUpdatedAt;
}
