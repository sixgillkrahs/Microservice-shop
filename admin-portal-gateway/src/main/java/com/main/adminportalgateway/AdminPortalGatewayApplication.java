package com.main.adminportalgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminPortalGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminPortalGatewayApplication.class, args);
    }

}
