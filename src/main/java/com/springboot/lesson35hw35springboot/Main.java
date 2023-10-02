package com.springboot.lesson35hw35springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;

@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger("logger");

    public static void main(String[] args) {
        logger.info("-----------------------Запуск SpringBootApplication --------------------------");
        logger.info("Поточний час: "+ new Timestamp(System.currentTimeMillis()));
        SpringApplication.run(Main.class, args);
    }

}
