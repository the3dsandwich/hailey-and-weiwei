package com.the3dsandwich.haileyandweiweibackend;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*
 * Copyright (c) 2024. the3dsandwich (Shann Wei Yeh)
 */
@Slf4j
@SpringBootApplication
public class HaileyAndWeiweiBackendApplication {

    @Value("${wedding-controller.allow-origin}")
    private String allowedOrigin;

    public static void main(String[] args) {
        SpringApplication.run(HaileyAndWeiweiBackendApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                log.info("allow origin: {}", allowedOrigin);
                registry.addMapping("/**")
                        .allowedOrigins(allowedOrigin);
            }
        };
    }

    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }

}
