package com.main.authservice.untils.mapper;

import com.main.authservice.dto.userAuth.CreateUserAuthDto;
import com.main.authservice.dto.userAuth.UserAuthDto;
import com.main.authservice.model.Roles;
import com.main.authservice.model.UserAuth;

import java.util.Date;
import java.util.UUID;

public class UserAuthMapper {
    public static UserAuth toUserAuth(CreateUserAuthDto input) {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(input.getEmail());
        userAuth.setPassword(input.getPassword());
        userAuth.setCreatedAt(new Date());
        userAuth.setLastUpdatedAt(new Date());
        userAuth.setId(UUID.randomUUID());
        userAuth.setIsActive(true);
        userAuth.setIsDeleted(false);
        userAuth.setLockUntil(null);
        return userAuth;
    }

    public static UserAuthDto toUserAuthDto(UserAuth userAuth) {
        UserAuthDto userAuthDto = new UserAuthDto();
        userAuthDto.setId(userAuth.getId());
        userAuthDto.setEmail(userAuth.getEmail());
        userAuthDto.setUserId(userAuth.getUserId());
        userAuthDto.setPassword(userAuth.getPassword());
        userAuthDto.setIsDeleted(userAuth.getIsDeleted());
        userAuthDto.setIsActive(userAuth.getIsActive());
        userAuthDto.setLockUntil(userAuth.getLockUntil());
        userAuthDto.setCreatedAt(userAuth.getCreatedAt());
        userAuthDto.setLastUpdatedAt(userAuth.getLastUpdatedAt());
        userAuthDto.setLastUpdatedPassword(userAuth.getLastUpdatedPassword());
        return userAuthDto;
    }
}
