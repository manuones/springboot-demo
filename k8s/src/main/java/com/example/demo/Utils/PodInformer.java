package com.example.demo.Utils;


import io.fabric8.kubernetes.api.model.Pod;

import io.fabric8.kubernetes.client.KubernetesClientException;
import io.fabric8.kubernetes.client.informers.ResourceEventHandler;
import io.fabric8.kubernetes.client.informers.SharedIndexInformer;
import io.fabric8.kubernetes.client.informers.SharedInformerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;


@Component
public class PodInformer implements ApplicationRunner{
    private static final Logger logger = Logger.getLogger(PodInformer.class.getSimpleName());


    @Autowired
    private K8sClient k8sClient;



    @Override
    public void run(ApplicationArguments args) throws Exception{
        Thread tStg = new Thread(() -> {
            getPodInformer("stg");
        });
        tStg.start();

        Thread tPre = new Thread(() -> {
            getPodInformer("pre");
        });
        tPre.start();
        logger.info("PodInformer init success");
    }
    public void getPodInformer(String env) {



        try{
            SharedInformerFactory sharedInformerFactory = k8sClient.getK8sClient(env).informers();
            SharedIndexInformer<Pod> podInformer = sharedInformerFactory.sharedIndexInformerFor(Pod.class,5 * 60 * 1000L);
            //SharedIndexInformer<Pod> podInformer = sharedInformerFactory.sharedIndexInformerFor(Pod.class, PodList.class, 1000L);
            logger.info("Informer factory initialized.");
            final CountDownLatch informerCompleted = new CountDownLatch(1);
            podInformer.addEventHandler(
                    new ResourceEventHandler<Pod>() {
                        @Override
                        public void onAdd(Pod pod) {
                            //handlePodObject("ADDED", pod,env);
                        }


                        @Override
                        public void onUpdate(Pod oldPod, Pod newPod) {
                            //handleUpdatePodObject("UPDATED", oldPod,newPod,env);
                        }

                        @Override
                        public void onDelete(Pod pod, boolean deletedFinalStateUnknown) {
                            handlePodObject("DELETED", pod,env);
                        }
                    }
            );
            logger.info("Starting all registered informers!!!");
            sharedInformerFactory.addSharedInformerEventListener(
                    exception -> {
                        logger.log(Level.SEVERE,"Exception occurred, but caught " + exception.getMessage(), exception);
                    }
            );
            sharedInformerFactory.startAllRegisteredInformers();
            informerCompleted.await(); //hang out forever
        } catch (KubernetesClientException e) {
            Thread.currentThread().interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    private void handleUpdatePodObject(String event, Pod oldPod,Pod newPod,String env) {


        StringBuilder builder = new StringBuilder()
                .append(env+"  ")
                .append(event)
                .append(" pod ").append(newPod.getMetadata().getName())
                .append(" created @").append(newPod.getMetadata().getCreationTimestamp())
                .append(" state ").append(newPod.getStatus().getPhase())
                .append(" pod ").append(oldPod.getMetadata().getName())
                .append(" created @").append(oldPod.getMetadata().getCreationTimestamp())
                .append(" state ").append(oldPod.getStatus().getPhase());
        logger.info(builder.toString());

    }

    private void handlePodObject(String event, Pod pod ,String env) {

        StringBuilder builder = new StringBuilder()
                .append(env+"  ")
                .append(event)
                .append(" pod ").append(pod.getMetadata().getName())
                .append(" created @").append(pod.getMetadata().getCreationTimestamp())
                .append(" state ").append(pod.getStatus().getPhase());
        logger.info(builder.toString());




    }
}

