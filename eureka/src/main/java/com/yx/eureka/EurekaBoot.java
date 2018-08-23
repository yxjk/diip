package com.yx.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaBoot {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaBoot.class).run(args);
    }
}
