package com.main.authservice.untils.mapper;

import com.main.authservice.dto.permission.CreatePermissionDto;
import com.main.authservice.dto.permission.PermissionDto;
import com.main.authservice.model.Permission;

import java.util.Date;
import java.util.UUID;

public class PermissionMapper {
    public static PermissionDto toPermissionDto(Permission permission) {
        return PermissionDto.builder()
                .id(permission.getId())
                .name(permission.getName())
                .description(permission.getDescription())
                .code(permission.getCode())
                .isDeleted(permission.getIsDeleted())
                .isActive(permission.getIsActive())
                .createdAt(permission.getCreatedAt())
                .lastUpdatedAt(permission.getLastUpdatedAt())
                .build();
    }

    public static Permission toPermission(CreatePermissionDto createPermissionDto) {
        return Permission.builder()
                .id(Long.valueOf(UUID.randomUUID().toString()))
                .name(createPermissionDto.getName())
                .description(createPermissionDto.getDescription())
                .code(createPermissionDto.getCode())
                .isDeleted(false)
                .isActive(true)
                .createdAt(new Date())
                .lastUpdatedAt(new Date())
                .build();
    }
}
