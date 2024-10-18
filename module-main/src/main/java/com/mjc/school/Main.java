package com.mjc.school;

import com.mjc.school.controller.view.AppView;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        context.getBean(AppView.class);
    }
}
