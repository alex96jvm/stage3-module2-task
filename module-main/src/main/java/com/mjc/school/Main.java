package com.mjc.school;

import com.mjc.school.controller.view.View;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        View view = context.getBean(View.class);
        view.start();
    }
}
