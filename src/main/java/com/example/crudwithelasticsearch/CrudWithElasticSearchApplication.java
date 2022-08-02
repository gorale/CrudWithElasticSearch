package com.example.crudwithelasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class CrudWithElasticSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudWithElasticSearchApplication.class, args);
    }

}
