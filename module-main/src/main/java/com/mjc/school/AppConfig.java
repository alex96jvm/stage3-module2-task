package com.mjc.school;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan({"com.mjc.school.main", "com.mjc.school.controller", "com.mjc.school.service", "com.mjc.school.repository"})
@EnableAspectJAutoProxy
public class AppConfig {
}
