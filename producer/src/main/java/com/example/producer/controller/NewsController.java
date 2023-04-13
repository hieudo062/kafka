package com.example.producer.controller;

import com.example.producer.model.NewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("create")
    public void create(@RequestBody NewsDto news) {
        kafkaTemplate.send("vip", news).addCallback(new KafkaSendCallback<String, Object>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                // handle success
                System.out.println(result.getRecordMetadata().partition());
            }
            @Override
            public void onFailure(KafkaProducerException e) {
                // handle failure,
                e.printStackTrace();
            }
        });
        kafkaTemplate.send("normal", news).addCallback(new KafkaSendCallback<String, Object>() {
            @Override
            public void onSuccess(SendResult<String, Object> result) {
                // handle success
                System.out.println(result.getRecordMetadata().partition());
            }
            @Override
            public void onFailure(KafkaProducerException e) {
                // handle failure,
                e.printStackTrace();
            }
        });
    }

}
