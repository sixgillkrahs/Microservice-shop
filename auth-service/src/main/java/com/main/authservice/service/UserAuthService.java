package com.main.authservice.service;

import com.main.authservice.dto.user.UserDto;
import com.main.authservice.dto.userAuth.CreateUserAuthDto;
import com.main.authservice.dto.userAuth.LoginDto;
import com.main.authservice.dto.userAuth.UserAuthDto;
import com.main.common.response.ResponseAPI;

import java.util.UUID;

public interface UserAuthService {
    public ResponseAPI<Boolean> createUser(CreateUserAuthDto input);
    public ResponseAPI<UserDto> getUserById(UUID id);
    public ResponseAPI<String> validateToken(String token);
    public ResponseAPI<String> login(String userName,String role);
}
