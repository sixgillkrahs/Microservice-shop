package com.main.userservice.service;

import com.main.common.response.ResponseAPI;
import com.main.userservice.dto.UsersUpdateDto;
import com.main.userservice.dto.UsersCreateDto;
import com.main.userservice.dto.UsersResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface UsersService {
    public ResponseAPI<UsersResponse> createUser(UsersCreateDto input);
    public ResponseAPI<UsersResponse> getUserById(UUID id);
    public ResponseAPI<UsersResponse> updateUserById(UUID id, UsersUpdateDto input);
    public ResponseAPI<Boolean> deleteUserById(UUID id);
    public ResponseAPI<Page<UsersResponse>> getUsersListPaging(Pageable pageable);
    public ResponseAPI<List<UsersResponse>> getAllUsersActive();
}
