package com.main.notificationservice.service.impl;

import com.main.notificationservice.model.otp.OTP;
import com.main.notificationservice.repository.ConfigRepository;
import com.main.notificationservice.repository.OTPRepository;
import com.main.notificationservice.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class OTPServiceImpl implements OTPService {
    @Autowired
    private OTPRepository otpRepository;
    @Autowired
    private ConfigRepository configRepository;

    @Override
    public void sendOTP(String phone) {
        OTP otp = new OTP();
        otp.setPhone(phone);
        otp.setOtp(generateOtp());
        String message = "Phone number: " + phone +", OTP is: " + otp.getOtp();
        // lưu OTP vào redis với thời gian sống là 24h
        otpRepository.save("OTP_"+phone,otp,60 * 60 * 24);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(getUri(message), String.class);
    }

    @Override
    public boolean verifyOTP(String phone, String otp) {
        OTP otpInDb = otpRepository.findByKey("OTP_"+phone);
        if (otpInDb == null) {
            return false;
        }
        if (!otpInDb.getOtp().equals(otp)) {
            return false;
        }
        return true;
    }

    @Override
    public OTP getOTP(String phone) {
        return otpRepository.findByKey(phone);
    }

    private String generateOtp() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder randomString = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }
        return randomString.toString();
    }

    private String getUri(String message) {
        String url_telegram_notice = configRepository.findByKey("URL_TELEGRAM_NOTICE").getValue();
        return url_telegram_notice + message;
    }
}
