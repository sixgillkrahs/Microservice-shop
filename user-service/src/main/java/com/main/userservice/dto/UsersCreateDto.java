package com.main.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsersCreateDto {
    @NotBlank(message = "Full name is required")
    private String fullName;
    private String avatar = "default";
    @NotNull(message = "Date of birth is required")
    private Date dob;
    private String phone;
    private String phonePrefix = "+84";
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank
    private String gender;
    private String address;
    private String searchInfo;
}
