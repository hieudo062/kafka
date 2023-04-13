package com.example.producer.controller;

import com.example.producer.model.AccountDto;
import com.example.producer.model.MessageDto;
import com.example.producer.model.StatisticDto;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("create")
    public AccountDto create(@RequestBody AccountDto accountDto) {
        StatisticDto statisticDto = new StatisticDto("Account " + accountDto.getEmail() + " is created", new Date());

        // send notification
        MessageDto messageDto = new MessageDto();
        messageDto.setTo(accountDto.getEmail());
        messageDto.setToName(accountDto.getName());
        messageDto.setSubject("Subject");
        messageDto.setContent("Content");

        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send("notification", messageDto).addCallback(new KafkaSendCallback<String, Object>() {
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
            kafkaTemplate.send("statistic", statisticDto);
        }

        return accountDto;
    }

}
