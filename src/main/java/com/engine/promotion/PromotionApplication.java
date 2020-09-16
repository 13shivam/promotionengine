package com.engine.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PromotionApplication {

    public static void main(String[] args) {
        SpringApplication.run(PromotionApplication.class, args);
    }

}
