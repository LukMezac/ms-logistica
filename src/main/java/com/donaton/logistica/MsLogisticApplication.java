package com.donaton.logistica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.donaton.logistica.model")
public class MsLogisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsLogisticApplication.class, args);
    }
}