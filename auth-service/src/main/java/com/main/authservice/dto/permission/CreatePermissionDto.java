package com.main.authservice.dto.permission;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePermissionDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
    private String description;
    @NotBlank(message = "Code is mandatory")
    private String code;
}
