package com.revert.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * xiecong
 */
@Component
public class ConsumerFatory {

    @KafkaListener(topics = "test222", groupId = "ddddd")
    public void listen(ConsumerRecord<?,String> record){
        System.out.println("========= 消费者===========");
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }

    /** 开启广播 同一个topics 不同groupid*/
    @KafkaListener(topics = "test222", groupId = "ddddd2")
    public void listen2(ConsumerRecord<?,String> record){
        System.out.println("========= 消费者===========");
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
    }

    @KafkaListener(topics = "demo", groupId = "demo2")
    public void listen3(ConsumerRecord<?,String> record, Acknowledgment ack){
        System.out.println("========= 消费者===========");
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
//        if(value.indexOf("2019-03-22T23:21:17.606") > -1 || value.indexOf("error") > -1){
//            System.out.println("······该消息未能消费·····");
//        }else {
            ack.acknowledge();
            System.out.println("手动提交消费·····");
//        }
    }

    ExecutorService threadPool = new ThreadPoolExecutor(
            10,
            10,
            3000L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new ThreadPoolExecutor.CallerRunsPolicy()
    );


    Map<String, List<ConsumerRecord<?,String>>> map = new ConcurrentHashMap<>(12);

    /**
     * 顺序消费
     * */
    @KafkaListener(topics = "orderMsg-1", groupId = "orderMsg-gId01")
    public void listen4(ConsumerRecord<?,String> record, Acknowledgment ack){
        String key = record.key().toString();
        List<ConsumerRecord<?,String>> list = null;
        if((list = map.get(key)) == null){
            list = new ArrayList<>(4);
        }
        list.add(record);
        map.put(key, list);
        ack.acknowledge();
    }

    /** 可以不用Scheduled定时器， 可以用初始化容器是就调用这方法（@PostConstruct）*/
    @Scheduled(cron="0/5 * * * * ? ")
    public void aaa() throws InterruptedException {
        //死循环
        while(true){
            if(map.size() == 0){
                return;
            }
            //取出满足3个条件
            Map<String, List<ConsumerRecord<?,String>>> newMap = new ConcurrentHashMap<>(12);
            map.forEach((key, val) ->{
                boolean flag = val.size() == 3;
                if(flag){
                    newMap.put(key, val);
                }
            });
            //计数器
            CountDownLatch countDownLatch = new CountDownLatch(newMap.size());
            newMap.forEach((key, val) ->{
                threadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(val);
                        //删除原有的 或者 清空val值
                        map.remove(key);
                        //计数器减一
                        countDownLatch.countDown();
                    }
                });
            });
            //等待计数器为0
            countDownLatch.await();
        }
    }

}
