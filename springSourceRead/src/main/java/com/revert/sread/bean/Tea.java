package com.revert.sread.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * xiecong
 */
@Configuration
public class Tea {

    private Student student;

    private String name;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tea{" +
                "student=" + student +
                ", name='" + name + '\'' +
                '}';
    }
}
