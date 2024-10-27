package com.mjc.school;

import com.mjc.school.validation.ValidationAspect;
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
