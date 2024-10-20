package com.main.userservice.service.Impl;

import com.main.common.exception.ErrorCode;
import com.main.common.exception.HandleRuntimeException;
import com.main.common.response.ResponseAPI;
import com.main.userservice.Utils.Mapper.UserSMapper;
import com.main.userservice.dto.UsersCreateDto;
import com.main.userservice.dto.UsersResponse;
import com.main.userservice.dto.UsersUpdateDto;
import com.main.userservice.model.Users;
import com.main.userservice.repository.UsersRepository;
import com.main.userservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsersServiceimpl  implements UsersService {

    @Autowired
    private UsersRepository repository;


    @Override
    public ResponseAPI<UsersResponse> createUser(UsersCreateDto input) {
        if(repository.findByEmail(input.getEmail()) != null){
            throw new HandleRuntimeException(ErrorCode.USER_ALREADY_EXISTS);
        }
        if(repository.findByPhone(input.getPhone()) != null){
            throw new HandleRuntimeException(ErrorCode.USER_ALREADY_EXISTS);
        }
        try {
            Users user = UserSMapper.toUser(input);
            Users savedUser = repository.save(user);
            return ResponseAPI.successResponse(UserSMapper.toUserResponse(savedUser));
        }
        catch (Exception e){
            return ResponseAPI.errorResponse(ErrorCode.FAILED);
        }
    }

    @Override
    public ResponseAPI<UsersResponse> getUserById(UUID id) {
        try {
            UsersResponse response = UserSMapper.toUserResponse(repository.findById(id).orElseThrow());
            return ResponseAPI.successResponse(response);
        } catch (Exception e){
            return ResponseAPI.errorResponse(ErrorCode.FAILED);
        }
    }

    @Override
    public ResponseAPI<UsersResponse> updateUserById(UUID id, UsersUpdateDto input) {
        Users users = repository.findById(id).orElseThrow();
        if(users == null){
            throw new HandleRuntimeException(ErrorCode.ITEM_NOT_FOUND);
        }
        try {
            users.setFullName(input.getFullName());
            users.setAvatar(input.getAvatar());
            users.setDob(input.getDob());
            users.setGender(input.getGender());
            users.setAddress(input.getAddress());
            users.setSearchInfo(input.getSearchInfo());
            users.setLastUpdatedAt(new Date());
            users.setLastUpdatedBy("system");
            Users savedUser = repository.save(users);
            return ResponseAPI.successResponse(UserSMapper.toUserResponse(savedUser));
        }catch (Exception e){
            return ResponseAPI.errorResponse(ErrorCode.FAILED);
        }
    }

    @Override
    public ResponseAPI<Boolean> deleteUserById(UUID id) {
        Users users = repository.findById(id).orElseThrow();
        if(users == null){
            throw new HandleRuntimeException(ErrorCode.ITEM_NOT_FOUND);
        }
        if(users.getIsDeleted()){
            throw new HandleRuntimeException(ErrorCode.ITEM_NOT_FOUND);
        }
        try {
            users.setIsDeleted(true);
            repository.save(users);
            return ResponseAPI.successResponse(true);
        }catch (Exception e){
            return ResponseAPI.errorResponse(ErrorCode.FAILED);
        }
    }

    @Override
    public ResponseAPI<Page<UsersResponse>> getUsersListPaging(Pageable pageable) {
        Page<Users> users = repository.findAll(pageable);
        if(users.isEmpty()){
            throw new HandleRuntimeException(ErrorCode.ITEM_NOT_FOUND);
        }
        try {
            List<UsersResponse> responseList = users.getContent().stream()
                    .map(UserSMapper::toUserResponse)
                    .toList();
            return ResponseAPI.successResponse(new PageImpl<>(responseList, pageable, users.getTotalElements()));
        } catch (Exception e){
            return ResponseAPI.errorResponse(ErrorCode.FAILED);
        }
    }

    @Override
    public ResponseAPI<List<UsersResponse>> getAllUsersActive() {
        List<Users> users = new ArrayList<>();
        try {
            users = repository.findByIsActive(true);
        }catch (Exception e){
            throw new HandleRuntimeException(ErrorCode.FAILED);
        }
        if(users.isEmpty()){
            throw new HandleRuntimeException(ErrorCode.ITEM_NOT_FOUND);
        }
        List<UsersResponse> responseList = users.stream()
                .map(UserSMapper::toUserResponse)
                .toList();
        return ResponseAPI.successResponse(responseList);
    }

}
