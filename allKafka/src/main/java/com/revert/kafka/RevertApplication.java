package com.revert.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.revert.kafka"})
@EnableScheduling
public class RevertApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RevertApplication.class,args);
    }
}
