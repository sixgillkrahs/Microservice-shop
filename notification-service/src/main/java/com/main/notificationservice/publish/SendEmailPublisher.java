package com.main.notificationservice.publish;

import io.nats.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class SendEmailPublisher {
    @Value("${job.nats.topic}")
    private String topic;
    @Autowired
    private Connection connection;

    public void sendEmail(String message) {
        connection.publish(topic, message.getBytes(StandardCharsets.UTF_8));
    }
}
