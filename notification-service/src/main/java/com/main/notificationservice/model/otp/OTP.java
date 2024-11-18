package com.main.notificationservice.model.otp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OTP {
    private String phone;
    private String otp;
}
