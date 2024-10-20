package com.main.authservice.service;

import com.main.common.response.ResponseAPI;

public interface JwtService {
    public void validateToken(final String token);
    public ResponseAPI<String> generateToken(String userName,String role);
}
