package dev.chanwu.bootbasic.config;

import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "configuration-base")
public class ServiceConfiguration {
    private String url;
}
