package com.main.notificationservice.controller;

import com.main.notificationservice.dto.common.SendEmailRequest;
import com.main.notificationservice.model.otp.OTP;
import com.main.notificationservice.service.EmailSenderService;
import com.main.notificationservice.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
public class NotificationController {
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private OTPService otpService;


    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody @Validated SendEmailRequest sendEmailRequest) {
        emailSenderService.sendEmail(sendEmailRequest.getTo(), sendEmailRequest.getSubject(), sendEmailRequest.getText());
        return "Email sent successfully!";
    }

    @PostMapping("/otp")
    public String sendOTP(@RequestParam String phone) {
        otpService.sendOTP(phone);
        return "OTP sent successfully!";
    }

    @GetMapping("/otp")
    public OTP getOTP(@RequestParam String phone) {
        return otpService.getOTP(phone);
    }

    @GetMapping("/vetify")
    public Boolean vetifyOTP(@RequestParam String phone ,@RequestParam String otp) {
        return otpService.verifyOTP(phone,otp);
    }
}
