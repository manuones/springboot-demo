package com.example.demo.ServiceImpl;


import java.util.*;


import com.example.demo.Service.K8sService;
import com.example.demo.Utils.K8sClient;
import io.fabric8.kubernetes.client.dsl.ExecListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.client.dsl.ExecWatch;
import okhttp3.Response;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@org.springframework.stereotype.Service
public class K8sServiceImpl implements K8sService {

    private static final Logger logger = LoggerFactory.getLogger(K8sServiceImpl.class);


    @Autowired
    private K8sClient k8sClient;

    @Override
    public List<Pod> getPod(String env,String namespace,String name){
        try {
            Map<String, String> matchLabels = k8sClient.getK8sClient(env).apps()
                    .deployments()
                    .inNamespace(namespace)
                    .withName(name)
                    .get()
                    .getSpec()
                    .getSelector()
                    .getMatchLabels();
            // 获取PodList
            List<Pod> items = k8sClient.getK8sClient(env).pods().inNamespace(namespace).withLabels(matchLabels).list().getItems();

            return items;

        } catch (Exception e) {
            logger.error("getPod Service Error,No name:"+name+"  env:"+env+"   namespace:"+namespace);
            return null;
        }


    }

    @Override
    public Set<String> getAppIdList(String env,String namespace){

        Set<String> result=new HashSet<>();

        List<Pod> podList= k8sClient.getK8sClient(env).pods().inNamespace(namespace).list().getItems();
        for(Pod item:podList){
            result.add(item.getMetadata().getLabels().get("app"));
        }


        return result;

    }

    @Override
    public String execLsDir(String env,String namespace,String podName,String cmd){
        CountDownLatch execLatch = new CountDownLatch(1);
        class MyPodExecListener implements ExecListener {
            @Override
            public void onOpen(Response response) {

                logger.info("Shell was opened");
            }

            @Override
            public void onFailure(Throwable throwable, Response response) {
                logger.info("Some error encountered");
                execLatch.countDown();
            }

            @Override
            public void onClose(int i, String s) {
                logger.info("Shell Closing");
                execLatch.countDown();
            }
        }
        String result="";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayOutputStream error = new ByteArrayOutputStream();

            ExecWatch execWatch = k8sClient.getK8sClient(env).pods().inNamespace(namespace).withName(podName)
                    .writingOutput(out)
                    .writingError(error)
                    .usingListener(new MyPodExecListener())
                    .exec("ls", cmd);

            boolean latchTerminationStatus = execLatch.await(30, TimeUnit.SECONDS);
            if (!latchTerminationStatus) {
                logger.warn("Latch could not terminate within specified time");
            }
            result=out.toString();
            if(result.equals("")){
                result=error.toString();
                logger.info("Exec ERROR: {} ",error);
            }else{
                logger.info("Exec Output: {} ",out);
            }
            execWatch.close();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            result="Interrupted while waiting for the exec: "+ie.getMessage().toString();
            logger.warn("Interrupted while waiting for the exec: {}", ie.getMessage());
        }
        return result;
    }



    @Override
    public void getNameSpace(String env){
        k8sClient.getK8sClient(env).namespaces()
                .list()
                .getItems()
                .stream()
                .map(Namespace::getMetadata)
                .map(ObjectMeta::getName)
                .forEach(logger::info);

    }

}

