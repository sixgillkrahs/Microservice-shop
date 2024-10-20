package com.main.authservice.service.Impl;

import com.main.authservice.dto.user.UserDto;
import com.main.authservice.dto.userAuth.CreateUserAuthDto;
import com.main.authservice.dto.userAuth.LoginDto;
import com.main.authservice.dto.userAuth.UserAuthDto;
import com.main.authservice.model.Roles;
import com.main.authservice.model.UserAuth;
import com.main.authservice.repository.RolesRepository;
import com.main.authservice.repository.UserAuthRepository;
import com.main.authservice.service.JwtService;
import com.main.authservice.service.UserAuthService;
import com.main.authservice.untils.mapper.UserAuthMapper;
import com.main.common.exception.ErrorCode;
import com.main.common.exception.HandleRuntimeException;
import com.main.common.response.ResponseAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;

@Service
public class UserAuthServiceImpl implements UserAuthService {
    @Autowired
    private UserAuthRepository userAuthRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseAPI<Boolean> createUser(CreateUserAuthDto input) {
//        if(userAuthRepository.findByEmail(input.getEmail()) != null){
//            throw new HandleRuntimeException(ErrorCode.USER_ALREADY_EXISTS);
//        }
        try {
            UserAuth userAuth = UserAuthMapper.toUserAuth(input);
            userAuth.setRolesId(rolesRepository.findById(1L).orElseThrow());
            userAuth.setPassword(passwordEncoder.encode(input.getPassword()));
            UUID newId = UUID.randomUUID();
            userAuth.setUserId(newId);
            ResponseEntity<ResponseAPI> booleanResponseEntity = restTemplate.postForEntity("http://localhost:8001/api/users/create", UserDto.builder()
                    .id(newId)
                    .email(input.getEmail())
                    .fullName(input.getFullName())
                    .dob(input.getDob())
                    .avatar(input.getAvatar())
                    .createdBy("system")
                    .createdAt(new Date())
                    .phone(input.getPhone())
                    .address(input.getAddress())
                    .isDeleted(false)
                    .phonePrefix(input.getPhonePrefix())
                    .searchInfo(input.getSearchInfo())
                    .lastUpdatedAt(new Date())
                    .lastUpdatedBy("system")
                    .isActive(true)
                    .gender(input.getGender())
                    .build(), ResponseAPI.class);
            if(booleanResponseEntity.getBody().getCode() != 200){
                return ResponseAPI.errorResponse(ErrorCode.FAILED);
            }
            userAuthRepository.save(userAuth);
            return ResponseAPI.successResponse(true);
        }
        catch (Exception e){
            return ResponseAPI.errorResponse(ErrorCode.FAILED);
        }
    }

    public ResponseAPI<UserDto> getUserById(UUID id) {
//        UserAuth userAuth = userAuthRepository.findById(id).orElseThrow(() -> new HandleRuntimeException(ErrorCode.ITEM_NOT_FOUND));
        String url = "http://localhost:8001/api/users/profile/" + id;
        ResponseAPI<UserDto> userAuthDto = restTemplate.getForObject(url, ResponseAPI.class);
        if(userAuthDto.getCode() != 200){
            throw new HandleRuntimeException(ErrorCode.ITEM_NOT_FOUND);
        }
        return userAuthDto;
    }

    @Override
    public ResponseAPI<String> validateToken(String token) {
        jwtService.validateToken(token);
        return ResponseAPI.successResponse("Token is valid");
    }

    @Override
    public ResponseAPI<String> login(String userName, String role) {
        return jwtService.generateToken(userName,role);
    }
}
