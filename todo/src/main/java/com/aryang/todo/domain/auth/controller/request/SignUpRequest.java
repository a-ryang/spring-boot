package com.aryang.todo.domain.auth.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record SignUpRequest(

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(min = 2, max = 100, message = "닉네임은 2~20자 이내로 입력해주세요.")
        @Pattern(regexp = "^(?=.*[가-힣A-Za-z])[가-힣A-Za-z\\d]*$", message = "닉네임은 1개 이상의 한글 또는 영문자를 포함하여 입력해주세요.")
        String nickname,

        @NotBlank(message = "이메일을 입력해주세요.")
        @Size(max = 100, message = "이메일은 100자 이내로 입력해주세요.")
        @Email(message = "올바른 이메일 형식을 입력해주세요.")
        String email,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,20}$",
                message = "비밀번호는 영문자, 숫자, 특수문자를 각각 최소 1개 이상 포함하여 2~20자 이내로 입력해주세요.")
        String password

) {
}
