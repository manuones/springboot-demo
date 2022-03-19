package com.example.demo.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/hello")
	public String hello(@RequestParam(value = "cmd", defaultValue = "ifconfig") String cmd) {

        // cmd="ping www.bing.com";

        String result=execCmd(cmd);
  
	    return String.format("Hello %s!", result);
	}

    private String execCmd(String StringCmd){
        
        StringBuilder result=new StringBuilder();

        Runtime runtime = Runtime.getRuntime();
        Process p = null;
        try {
            p = runtime.exec(StringCmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream(), "GBK"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                result.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            result.append(e.toString());
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        
        return result.toString();

    }
    
}
