package com.main.authservice.controller;

import com.main.authservice.dto.user.UserDto;
import com.main.authservice.dto.userAuth.CreateUserAuthDto;
import com.main.authservice.dto.userAuth.LoginDto;
import com.main.authservice.service.JwtService;
import com.main.authservice.service.UserAuthService;
import com.main.common.exception.ErrorCode;
import com.main.common.response.ResponseAPI;
import feign.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserAuthService authService;

    @PostMapping("/register")
    public ResponseAPI<Boolean> addNewUser(@Validated @RequestBody CreateUserAuthDto input) {
        return authService.createUser(input);
    }

    @GetMapping("/profile/{id}")
    public ResponseAPI<UserDto> profile(@PathVariable UUID id ) {
        return authService.getUserById(id);
    }


    @GetMapping("/validate")
    public String validateToken() {
//        return authService.validateToken(token);
        return "Token is valid";
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }

    @PostMapping("/login")
    public ResponseAPI <String> login(@Validated @RequestBody LoginDto input) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));
            return authService.login(input.getEmail(), "user");
        }catch (Exception e){
            return ResponseAPI.errorResponse(ErrorCode.FORBIDDEN);
        }
    }
}
