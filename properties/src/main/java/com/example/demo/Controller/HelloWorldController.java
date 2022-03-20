package com.example.demo.Controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Config.GetProperties;
import com.example.demo.Config.StudentYml;

@RestController
public class HelloWorldController {

    @Autowired
    GetProperties getProperties;
    @Autowired
    Environment environment;
    @Autowired
    StudentYml studentYml;
    

    @GetMapping("/value")
	public String hello(@RequestParam(value = "hello", defaultValue = "world") String cmd) {

        String result=getProperties.getConfig();

  
	    return String.format("Hello %s!", result);
	}

    @GetMapping("/env")
	public String helloEnv(@RequestParam(value = "hello", defaultValue = "world") String cmd) {

        String result=environment.getProperty("environment.config.web-config.name");

  
	    return String.format("Hello %s!", result);
	}

    @GetMapping("/yaml")
	public String helloYaml(@RequestParam(value = "hello", defaultValue = "world") String cmd) {

        String result=studentYml.getWebConfigs().getName();

  
	    return String.format("Hello %s!", result);
	}

   
    
}
