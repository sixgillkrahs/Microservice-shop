package com.main.userservice.Utils.Mapper;

import com.main.userservice.dto.UsersCreateDto;
import com.main.userservice.dto.UsersResponse;
import com.main.userservice.model.Users;

import java.util.Date;
import java.util.UUID;

public class UserSMapper {
    public static UsersResponse toUserResponse(Users user) {
        return new UsersResponse(user.getId(),user.getFullName(),
                user.getAvatar(), user.getDob(),
                user.getPhone(), user.getPhonePrefix(),
                user.getEmail(), user.getGender(),
                user.getAddress(), user.getIsDeleted(),
                user.getIsActive(), user.getSearchInfo(),
                user.getCreatedAt(), user.getCreatedBy(),
                user.getLastUpdatedAt(), user.getLastUpdatedBy()
        );
    }

    public static Users toUser(UsersCreateDto userRequest) {
        return new Users(UUID.randomUUID(), userRequest.getFullName(),
                userRequest.getAvatar(), userRequest.getDob(),
                userRequest.getPhone(), userRequest.getPhonePrefix(),
                userRequest.getEmail(), userRequest.getGender(),
                userRequest.getAddress(), false,
               true, userRequest.getSearchInfo(),
                new Date(), "system",
                new Date(), "system"
        );
    }
}
