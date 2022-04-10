package com.example.demo.Controller;




import com.example.demo.Request.ExecPodRequestEntity;
import com.example.demo.Service.K8sService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;


@RestController
public class HelloWorldController {

   @Autowired
    K8sService k8sService;
    

    @GetMapping("/namespace")
	public ResponseEntity getNameSpace(@RequestParam(value = "env", defaultValue = "global") String env) {

        k8sService.getNameSpace(env);

        return ResponseEntity.ok().body("success");
	}
    @PostMapping("/execPod/listDir")
    public ResponseEntity execPod(@RequestBody ExecPodRequestEntity params){

        Map<String,Object> result= new HashMap<>();
        result.put("result","success");
        result.put("data",k8sService.execLsDir(params.getEnv(),params.getNamespace(),params.getPodName(),params.getCmd()));

        return ResponseEntity.ok().body(result);
    }



   
    
}
