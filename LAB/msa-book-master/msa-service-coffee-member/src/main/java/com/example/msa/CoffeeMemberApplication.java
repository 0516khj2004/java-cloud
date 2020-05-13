package com.example.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class CoffeeMemberApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoffeeMemberApplication.class, args);
    }
}
