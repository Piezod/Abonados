package com.piezod.abonadoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.piezod.abonadoservice.infrastructure.persistence")
public class AbonadoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbonadoServiceApplication.class, args);
    }
}
