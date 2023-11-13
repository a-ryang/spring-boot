package com.aryang.todo.domain.member.entity;

import com.aryang.todo.domain.member.enums.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private Long id;
    /** 0: email, 1: social-login */
    private LoginType loginType;

}
