package com.main.notificationservice.service;

import com.main.notificationservice.model.otp.OTP;

public interface OTPService {
    public void sendOTP(String phone);
    public boolean verifyOTP(String phone, String otp);
    public OTP getOTP(String phone);
}
