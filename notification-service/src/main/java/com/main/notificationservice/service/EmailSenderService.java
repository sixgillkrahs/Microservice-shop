package com.main.notificationservice.service;

public interface EmailSenderService {
    public void sendEmail(String to, String subject, String text);
}
