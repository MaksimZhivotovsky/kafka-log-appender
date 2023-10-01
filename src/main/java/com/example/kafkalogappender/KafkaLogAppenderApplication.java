package com.example.kafkalogappender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaLogAppenderApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(KafkaLogAppenderApplication.class, args);
//        HelloWorld helloWorld = new HelloWorld();
        HelloWorld.createLogs();
    }

}
