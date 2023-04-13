package com.example.notificationservice.service;

import com.example.notificationservice.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private JavaMailSender javaMailSender;

    private SpringTemplateEngine templateEngine;

    @Value("$(spring.mail.username)")
    private String from;

    @Override
    @Async
    public void sendEmail(MessageDto messageDto) {
        try {
            logger.info("START ... Sending Email");

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, StandardCharsets.UTF_8.name());

            // load template email with content
            Context context = new Context();
            context.setVariable("name", messageDto.getToName());
            context.setVariable("context", messageDto.getContent());
            String html = templateEngine.process("wellcome-email", context);

            // send email
            helper.setTo(messageDto.getTo());
            helper.setText(html,true);
            helper.setSubject(messageDto.getSubject());
            helper.setFrom(from);
            javaMailSender.send(message);

            logger.info("END... Email send success");
        } catch (Exception e) {
            logger.error("Email send with ERROR: " + e.getMessage());
        }

    }
}