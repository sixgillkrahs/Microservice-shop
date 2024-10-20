package com.main.userservice.controller;

import com.main.common.response.ResponseAPI;
import com.main.userservice.dto.UsersCreateDto;
import com.main.userservice.dto.UsersResponse;
import com.main.userservice.dto.UsersUpdateDto;
import com.main.userservice.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UsersService userService;

    @PostMapping("/create")
    public ResponseAPI<UsersResponse> createUser(@Validated @RequestBody UsersCreateDto input) {
        return userService.createUser(input);
    }

    @GetMapping("/profile/{id}")
    public ResponseAPI<UsersResponse> getUserById(@PathVariable("id") UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update-profile/{id}")
    public ResponseAPI<UsersResponse> updateUserById(@PathVariable("id") UUID id, @Validated @RequestBody UsersUpdateDto input) {
        return userService.updateUserById(id, input);
    }

    @PutMapping("/delete-profile/{id}")
    public ResponseAPI<Boolean> deleteUserById(@PathVariable("id") UUID id) {
        return userService.deleteUserById(id);
    }

    @GetMapping("/list-users-paging")
    public ResponseAPI<Page<UsersResponse>> getUsersListPaging(
            @RequestParam(required = false,defaultValue = "0") int pageIndex,
            @RequestParam(required = false,defaultValue = "20") int pageSize,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false,defaultValue = "0") String sortType
    ) {
        Pageable pageable;
        if( sortBy == null){
            pageable = PageRequest.of(pageIndex, pageSize);
        }else{
            Sort sort = sortType.equalsIgnoreCase("1") ?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
            pageable = PageRequest.of(pageIndex, pageSize, sort);
        }
        ResponseAPI<Page<UsersResponse>> usersListPaging = userService.getUsersListPaging(pageable);
        return usersListPaging;
    }

    @GetMapping("/list-users-active")
    public ResponseAPI<List<UsersResponse>> getAllUsersActive() {
        return userService.getAllUsersActive();
    }




}
