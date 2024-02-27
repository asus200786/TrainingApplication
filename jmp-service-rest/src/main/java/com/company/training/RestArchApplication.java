package com.company.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.company.training")
public class RestArchApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestArchApplication.class, args);
    }
}
