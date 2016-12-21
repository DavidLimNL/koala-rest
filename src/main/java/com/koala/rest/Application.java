package com.koala.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * Application entry point and configuration for Spring Boot.
 */
@SpringBootApplication
@Slf4j
public class Application extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Application.class);
    }
 
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }
}
