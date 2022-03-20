package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:config.properties"},encoding="utf-8")
public class GetProperties {

    @Value("${demo.config.web-configs.name}")
    private String name;
    @Value("${demo.config.web-configs.age}")
    private String age;

    public String getConfig() {
        return name+"-----"+age;
    }
}