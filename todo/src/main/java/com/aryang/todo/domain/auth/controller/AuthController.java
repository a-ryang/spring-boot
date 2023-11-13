package com.aryang.todo.domain.auth.controller;

import com.aryang.todo.domain.auth.controller.request.SignUpRequest;
import com.aryang.todo.domain.auth.service.AuthService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthService service;

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(
            @Valid @RequestBody final SignUpRequest body
    ){
        service.signUp(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
