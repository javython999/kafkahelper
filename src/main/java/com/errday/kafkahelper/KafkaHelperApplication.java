package com.errday.kafkahelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KafkaHelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaHelperApplication.class, args);
    }

}
