package com.mjc.school.annotation;

import com.mjc.school.request.NewsRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class NewsCommandProcessor {

    private final Map<String, Method> commandHandlers = new HashMap<>();
    private final Object controller;

    public NewsCommandProcessor(Object controller) {
        this.controller = controller;
        initializeCommandHandlers();
    }

    private void initializeCommandHandlers() {
        Method[] methods = controller.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CommandHandler.class)) {
                CommandHandler commandHandler = method.getAnnotation(CommandHandler.class);
                commandHandlers.put(commandHandler.value(), method);
            }
        }
    }

    public Object processCommand(String commandName, Map<String, Object> params) {
        Method method = commandHandlers.get(commandName);

        int paramCount = method.getParameterCount();
        Object[] args = new Object[paramCount];

        try {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    if (annotation instanceof CommandParam) {
                        String paramName = ((CommandParam) annotation).value();
                        args[i] = params.get(paramName);
                    } else if (annotation instanceof CommandBody) {
                        NewsRequest newsRequest = new NewsRequest(
                                (String) params.get("title"),
                                (String) params.get("content"),
                                (Long) params.get("authorId")
                        );
                        if (params.containsKey("id")) {
                            Long id = (Long) params.get("id");
                            newsRequest.setId(id);
                        }
                        args[i] = newsRequest;
                    }
                }
            }
            return method.invoke(controller, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Failed to execute command: " + commandName, e);
        }
    }
}
