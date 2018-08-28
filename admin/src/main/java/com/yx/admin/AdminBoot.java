package com.yx.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.yx.entity")
@SpringBootApplication
public class AdminBoot {

    public static void main(String[] args) {
        SpringApplication.run(AdminBoot.class, args);
    }
}
