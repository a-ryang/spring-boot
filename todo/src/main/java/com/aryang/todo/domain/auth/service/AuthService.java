package com.aryang.todo.domain.auth.service;

import com.aryang.todo.domain.auth.controller.request.SignUpRequest;

public interface AuthService {

    void signUp(SignUpRequest payload);

}
