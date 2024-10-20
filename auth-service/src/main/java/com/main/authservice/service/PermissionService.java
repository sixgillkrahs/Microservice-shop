package com.main.authservice.service;

import com.main.authservice.dto.permission.CreatePermissionDto;
import com.main.authservice.dto.permission.PermissionDto;
import com.main.authservice.dto.userAuth.CreateUserAuthDto;
import com.main.authservice.dto.userAuth.UserAuthDto;
import com.main.common.response.ResponseAPI;

public interface PermissionService {
    public ResponseAPI<PermissionDto> createPermission(CreatePermissionDto input);
}
