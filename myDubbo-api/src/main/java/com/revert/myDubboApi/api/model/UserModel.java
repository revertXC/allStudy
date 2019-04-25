package com.revert.myDubboApi.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * xiecong
 */
@Data
public class UserModel implements Serializable {
    public UserModel(){}
    public UserModel(String name, Integer age){
        this.name = name;
        this.age = age;
    }
    public UserModel(String name, Integer age, String provideName){
        this.name = name;
        this.age = age;
        this.provideName = provideName;
    }

    private String name;

    private Integer age;

    private String provideName;

}
