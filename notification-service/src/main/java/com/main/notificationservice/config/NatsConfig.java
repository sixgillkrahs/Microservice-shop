package com.main.notificationservice.config;

import io.nats.client.Connection;
import io.nats.client.Nats;
import io.nats.client.Options;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NatsConfig {
    @Value("${job.nats.url}")
    private String url;
    private String subject;

    @Bean
    public Connection getNatsConnection() throws Exception {
        Options options = Options.builder().server(url).build();
        return Nats.connect(options);
    }
}
