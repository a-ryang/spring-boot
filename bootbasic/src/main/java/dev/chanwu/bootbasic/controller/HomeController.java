package dev.chanwu.bootbasic.controller;

import dev.chanwu.bootbasic.config.ServiceConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {
    private final ServiceConfiguration configuration;

    @GetMapping("/configuration")
    public ServiceConfiguration getConfiguration() {
        return configuration;
    }
}
