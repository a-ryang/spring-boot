package com.aryang.todo.global.web;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SpaWebFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        if(!path.startsWith("/api") &&   // 경로가 "/api"로 시작하지 않는 경우
            !path.contains(".") &&       // 경로에 점(.)이 포함되지 않은 경우를 확인. 정적 파일(예: .css, .js)을 필터링에서 제외하기 위함
            path.matches("/(.*)"))       // 경로가 "/"로 시작하는지 확인
        {
            request.getRequestDispatcher("/").forward(request, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

}
