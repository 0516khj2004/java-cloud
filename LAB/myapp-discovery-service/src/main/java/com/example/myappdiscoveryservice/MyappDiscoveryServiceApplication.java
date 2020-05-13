package com.example.myappdiscoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MyappDiscoveryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappDiscoveryServiceApplication.class, args);
    }

}
