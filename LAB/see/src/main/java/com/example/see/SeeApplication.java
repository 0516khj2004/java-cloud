package com.example.see;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeeApplication.class, args);
    }

}
