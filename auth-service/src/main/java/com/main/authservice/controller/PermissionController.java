package com.main.authservice.controller;

import com.main.authservice.dto.permission.CreatePermissionDto;
import com.main.authservice.dto.permission.PermissionDto;
import com.main.authservice.service.PermissionService;
import com.main.common.response.ResponseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("/create")
    public ResponseAPI<PermissionDto> createPermission(@Validated @RequestBody CreatePermissionDto input){
        return permissionService.createPermission(input);
    }
}
