package com.davidlopez.practicaSockets.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(scanBasePackages = {"com.davidlopez.practicaSockets.config",
        "com.davidlopez.practicaSockets.controller",
        "com.davidlopez.practicaSockets.models",
        "com.davidlopez.practicaSockets.services",
})
@EnableMongoRepositories(basePackages = "com.davidlopez.practicaSockets.repositories")
public class DemoApplication {


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}

