package com.aryang.todo.domain.todo.entity;

import com.aryang.todo.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Todo extends BaseTimeEntity {
    private Long id;
    private Long memberId;
    private String content;
    private Boolean isDone;

    public void update(String content) {
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public void complete() {
        this.isDone = true;
        this.updatedAt = LocalDateTime.now();
    }

    public void uncomplete() {
        this.isDone = false;
        this.updatedAt = LocalDateTime.now();
    }

}
