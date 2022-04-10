package com.example.demo.Request;
import lombok.Data;

@Data
public class ExecPodRequestEntity {
    private String env;
    private String namespace;
    private String cmd;
    private String podName;
}
