package com.mjc.school.controller;

import com.mjc.school.service.validation.ValidationAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.mjc.school")
@EnableAspectJAutoProxy
public class AppConfig {
    @Bean
    public ValidationAspect validationAspect() {
        return new ValidationAspect();
    }
}
