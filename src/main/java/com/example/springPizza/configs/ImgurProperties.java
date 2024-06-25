package com.example.springPizza.configs;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "imgur")
public class ImgurProperties {
    private String clientId;
    private String clientSecret;
}
