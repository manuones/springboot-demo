package com.example.demo.Service;

import java.util.List;
import java.util.Set;

import io.fabric8.kubernetes.api.model.Pod;

public interface K8sService {

    List<Pod> getPod(String env,String namespace,String name);

    void getNameSpace(String env);

    String execLsDir(String env,String namespace,String podName,String cmd);

    Set<String> getAppIdList(String env,String namespace);



}
