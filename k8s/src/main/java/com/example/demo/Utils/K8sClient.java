package com.example.demo.Utils;


import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.apache.commons.io.IOUtils;

@Component
public class K8sClient {
    private final static Logger logger = LoggerFactory.getLogger(K8sClient.class);



    public KubernetesClient getK8sClient(String env) {
        try {

            //方法1

//            String token="k8s token";
//            Config build=new ConfigBuilder().withMasterUrl("k8s master url").withTrustCerts(true).withOauthToken(token).build();
//            return new DefaultKubernetesClient(build);

            //方法2
            String devData = IOUtils.toString(new ClassPathResource("k8s/kube-dev.conf").getInputStream(), "UTF-8");
            return new DefaultKubernetesClient(Config.fromKubeconfig(devData));


        } catch (Exception e) {
            logger.error("初始化 KubernetesClient " + env + " 失败！", e);
            return null;
        }
    }


}

