package com.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.company")
public class UniversityApp {

    public static void main(String[] args) {
        SpringApplication.run(UniversityApp.class, args);
    }

}
