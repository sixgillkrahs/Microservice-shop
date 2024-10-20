package com.main.authservice.service.Impl;

import com.main.authservice.dto.permission.CreatePermissionDto;
import com.main.authservice.dto.permission.PermissionDto;
import com.main.authservice.model.Permission;
import com.main.authservice.repository.PermissionRepository;
import com.main.authservice.service.PermissionService;
import com.main.authservice.untils.mapper.PermissionMapper;
import com.main.common.exception.ErrorCode;
import com.main.common.exception.HandleRuntimeException;
import com.main.common.response.ResponseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionRepository repository;


    @Override
    public ResponseAPI<PermissionDto> createPermission(CreatePermissionDto input) {
        if(repository.findByCode(input.getCode()) != null){
            throw new HandleRuntimeException(ErrorCode.ITEM_ALREADY_EXISTS);
        }
        if(repository.findByName(input.getName()) != null){
            throw new HandleRuntimeException(ErrorCode.ITEM_ALREADY_EXISTS);
        }
        try {
            Permission permissionDto = PermissionMapper.toPermission(input);
            repository.save(permissionDto);
            return ResponseAPI.successResponse(PermissionMapper.toPermissionDto(permissionDto));
        }catch (Exception e){
            return ResponseAPI.errorResponse(ErrorCode.FAILED);
        }
    }
}
