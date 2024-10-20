package com.main.userservice.repository;

import com.main.userservice.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> , PagingAndSortingRepository<Users, UUID> {
    Users findByEmail(String email);
    Users findByPhone(String phone);
    List<Users> findByIsActive(Boolean active);
}
