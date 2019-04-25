package com.revert.myDubboS.provider_.impi;

import com.alibaba.dubbo.config.annotation.Service;
import com.revert.myDubboApi.api.model.UserModel;
import com.revert.myDubboApi.api.openAble.UserInfo;
import org.springframework.beans.factory.annotation.Value;

/**
 * xiecong
 */
@Service(loadbalance = "roundrobin")
public class UserInfoImpi implements UserInfo {

    @Value("${dubbo.application.name}")
    private String provideName;


    private int i = 0 ;

    @Override
    public UserModel getUsers() {
//        System.out.println(i++);
//        System.out.println(1/0);
        System.out.println("有消费者调用······ 睡眠10s");
        try {
//            Thread.sleep(10000L);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("有消费者调用······ 睡眠10s 结束");
        return new UserModel("xiecong", 18, provideName);
    }
}
