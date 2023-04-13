package com.example.notificationservice.service.normal;

import com.example.notificationservice.entity.Normal;
import com.example.notificationservice.repo.NormalRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NormalService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final NormalRepo repo;

    @KafkaListener(id = "normalGroup", topics = "normal")
    public void listen(Normal normal) {
        logger.info("Received: " + normal.getTitle());
        repo.save(normal);
    }
}
