package com.team7.gameserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    //Single shared instance for making external HTTP requests
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // Single shared instance for hashing and verifying passwords using BCrypt
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}