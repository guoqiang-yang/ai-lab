package com.oscar.ailab.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableTransactionManagement
public class AiLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiLabApplication.class, args);
        log.info("I am Running ... ");
    }
}
