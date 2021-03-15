package com.laundrative_v1.app.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 @author Emre Caniklioglu
 @versio 1.0.0

 This is the config class for creating a access via a static variables to the Application Context
 object
 */

@Configuration
public class AppContextStatic implements ApplicationContextAware
{
    // Stored static variable

    static ApplicationContext applicationContext = null;

    public void setApplicationContext(ApplicationContext context) throws BeansException
    {
        applicationContext = context;
    }

    public static ApplicationContext getContext()
    {
        return applicationContext;
    }
}
