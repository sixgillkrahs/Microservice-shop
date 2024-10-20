package com.main.authservice.repository;

import com.main.authservice.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, UUID> , PagingAndSortingRepository<UserAuth,UUID> {
    Optional<UserAuth> findByEmail(String email);
    UserAuth findByEmailAndPassword(String email, String password);
}
