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
    NewTopic notification() {
        // topic name, partitionNumber, replicationNumber
        return new NewTopic("notification", 2 , (short) 1);
    }

    @Bean
    NewTopic statistic() {
        // topic name, partitionNumber, replicationNumber
        return new NewTopic("statistic", 2 , (short) 1);
    }
}
