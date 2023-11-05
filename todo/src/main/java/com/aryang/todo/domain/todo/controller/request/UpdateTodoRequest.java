package com.aryang.todo.domain.todo.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTodoRequest(

        @NotBlank(message = "내용을 입력해주세요.")
        @Size(max = 100, message = "100자 이내로 입력해주세요.")
        String content

) {
}
