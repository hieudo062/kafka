package com.example.notificationservice.service;

import com.example.notificationservice.model.MessageDto;

public interface EmailService {
    void sendEmail(MessageDto messageDto);
}
