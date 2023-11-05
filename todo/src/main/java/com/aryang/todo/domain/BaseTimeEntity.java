package com.aryang.todo.domain;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
public abstract class BaseTimeEntity {

    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    protected LocalDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    protected LocalDateTime updatedAt;

    @DateTimeFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    protected LocalDateTime deletedAt;

}
