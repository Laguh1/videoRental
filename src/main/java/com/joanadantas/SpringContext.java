package com.joanadantas;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext context;

    public SpringContext() {
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        context = context;
    }
}

