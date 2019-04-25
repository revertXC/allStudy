package com.revert.myDubboApi.api.openAble;

import com.revert.myDubboApi.api.model.UserModel;

/**
 * xiecong
 */
public class UserInfoMock implements UserInfo {

    @Override
    public UserModel getUsers() {
        System.out.println("······Mock降级处理");
        return new UserModel("降级处理···",111,"降级提供者11");
    }
}
