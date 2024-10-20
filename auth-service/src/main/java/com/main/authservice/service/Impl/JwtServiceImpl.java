package com.main.authservice.service.Impl;

import com.main.authservice.service.JwtService;
import com.main.common.exception.ErrorCode;
import com.main.common.response.ResponseAPI;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";

    @Override
    public void validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        } catch (Exception e) {
            throw new RuntimeException("Invalid token");
        }
    }

    @Override
    public ResponseAPI<String> generateToken(String userName,String role) {
       try {
           Map<String, Object> claims = new HashMap<>();
           claims.put("role", role);
           return ResponseAPI.successResponse(createToken(claims, userName));
       }catch (Exception e){
           return ResponseAPI.errorResponse(ErrorCode.FAILED);
       }
    }

    private String createToken(Map<String, Object> claims, String userName ) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
