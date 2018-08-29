package com.yx.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EntityScan("com.yx.entity")
@EnableDiscoveryClient
@SpringBootApplication
public class ScheduleBoot {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleBoot.class, args);
    }
}
