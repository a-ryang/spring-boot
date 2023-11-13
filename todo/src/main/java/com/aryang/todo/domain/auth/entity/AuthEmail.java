package com.aryang.todo.domain.auth.entity;

import com.aryang.todo.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthEmail extends BaseTimeEntity {

    private Long id;
    private Long memberId;
    private String email;
    private String password;

}
