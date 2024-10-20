package com.main.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersUpdateDto {
    @NotBlank(message = "Full name is required")
    private String fullName;
    private String avatar = "default";
    @NotNull(message = "Date of birth is required")
    private Date dob;
    @NotBlank
    private String gender;
    private String address;
    private String searchInfo;
}
