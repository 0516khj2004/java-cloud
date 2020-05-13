package com.example.myappconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class MyappConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyappConfigServiceApplication.class, args);
    }

}
