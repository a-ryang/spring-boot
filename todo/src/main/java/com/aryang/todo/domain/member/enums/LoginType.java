package com.aryang.todo.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoginType {
    EMAIL(0),
    SOCIAL_LOGIN(1);

    private int value;

}
