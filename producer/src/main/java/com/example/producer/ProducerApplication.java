package com.example.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    NewTopic vip() {
        // topic name, partitionNumber, replicationNumber
        return new NewTopic("vip", 2 , (short) 1);
    }

    @Bean
    NewTopic normal() {
        // topic name, partitionNumber, replicationNumber
        return new NewTopic("normal", 2 , (short) 1);
    }
}
