package com.aryang.todo.domain.todo.controller.response;

public record TodoResponse(
        Long id,
        String content,
        Boolean isDone
) {
}
