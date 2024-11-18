package com.main.notificationservice.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.main.notificationservice.model.otp.OTP;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Repository
@AllArgsConstructor
public class OTPRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public void save(String keyy, OTP otp,int time) {
        redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] key = keyy.getBytes(StandardCharsets.UTF_8);
            byte[] value = null;
            try {
                value = objectMapper.writeValueAsBytes(otp);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            connection.set(key, value);
            connection.expire(key, time);
            return null;
        });
    }



    public OTP findByKey(String keyy) {
        return (OTP) redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] key = keyy.getBytes(StandardCharsets.UTF_8);
            byte[] value = connection.get(key);
            if (value == null) {
                return null;
            }
            try {
                return objectMapper.readValue(value, OTP.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
