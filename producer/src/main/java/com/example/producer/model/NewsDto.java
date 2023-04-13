package com.example.producer.model;

import lombok.Data;

@Data
public class NewsDto {
    private Integer type;
    private String title;
    private String content;
}
