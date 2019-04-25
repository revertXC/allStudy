package com.revert.kafka.controller;

import com.revert.kafka.producer.ProducerFatory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;


/**
 * xiecong
 */
@RequestMapping("/api/v1/kafka")
@RestController
public class TestController {

    public static void main(String[] args) {
        Map a = new Hashtable();
        a.remove(null);
    }


    @Autowired
    private ProducerFatory producerFatory;

    @RequestMapping(method = RequestMethod.GET)
    public String sendTestTocip(@RequestParam(value = "msg", required = false) String msg){
        msg = StringUtils.isEmpty(msg) ? "Hello World" : msg;
        ListenableFuture<SendResult> listenableFuture =  producerFatory.testProducer(msg);
        listenableFuture.addCallback((success) -> {
            System.out.println("成功："+success);
        }, (error) ->{
            System.out.println("失败："+error);
        });
        return "success";
    }


    @RequestMapping(method = RequestMethod.GET, value = "2")
    public String sendTestTocip2(@RequestParam(value = "msg", required = false) String msg){
        msg = StringUtils.isEmpty(msg) ? "Hello World" : msg+" 生成时间："+ LocalDateTime.now();
        ListenableFuture<SendResult> listenableFuture =  producerFatory.nProducer("demo",msg);
        listenableFuture.addCallback((success) -> {
            System.out.println("成功："+success);
        }, (error) ->{
            System.out.println("失败："+error);
        });
        return "success";
    }


    CountDownLatch countDownLatch = new CountDownLatch(1);
    /***
     * 顺序消费Demo
     * @param msg
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "3")
    public String sendTestTocip3(@RequestParam(value = "msg", required = false) String msg) throws InterruptedException {
        String uuid = UUID.randomUUID().toString();
//        countDownLatch.await();
        for(int i=0; i<3 ;i++){
            msg = new String("key "+uuid+" 生成时间："+ LocalDateTime.now());
            ListenableFuture<SendResult> listenableFuture =  producerFatory.nProducer("orderMsg-1",uuid,msg);
            listenableFuture.addCallback((success) -> {
                System.out.println("成功："+success);
            }, (error) ->{
                System.out.println("失败："+error);
            });
        }
        return "success";
    }

    @RequestMapping("3/con")
    public String consumerLatch(){
        if(0 == countDownLatch.getCount()){
            countDownLatch = new CountDownLatch(1);
            return "初始化···下令";
        }
        countDownLatch.countDown();
        return countDownLatch.getCount()+"";
    }

}
