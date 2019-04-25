package com.revert.myDubboC.consumer_.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.revert.myDubboApi.api.model.UserModel;
import com.revert.myDubboApi.api.openAble.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * xiecong
 */
@RestController
@RequestMapping("api/v1/dubbo")
public class TestController {

    @Reference(retries = 1, cluster="failover", timeout = 1000, mock = "force:return+null")
    private UserInfo userInfo;

    @RequestMapping(method = RequestMethod.GET)
    public UserModel getUserInfo(){
        UserModel userModel = userInfo.getUsers();
        System.out.println(userModel);
        return userModel;
    }

}
