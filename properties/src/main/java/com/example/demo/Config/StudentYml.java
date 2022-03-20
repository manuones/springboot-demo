package com.example.demo.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "student.config")
@Data
public class StudentYml {
    
    
    private WebConfigs webConfigs = new WebConfigs();

    @Data
    public static class WebConfigs {
        private String name;
        private String age;
    }
}
