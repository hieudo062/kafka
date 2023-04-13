package com.example.notificationservice.service.vip;

import com.example.notificationservice.entity.Vip;
import com.example.notificationservice.repo.VipRepo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VipService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VipRepo repo;

    @KafkaListener(id = "vipGroup", topics = "vip")
    public void listen(Vip vip) {
        logger.info("Received: " + vip.getTitle());
        repo.save(vip);
    }
}
