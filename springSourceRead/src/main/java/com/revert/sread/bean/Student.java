package com.revert.sread.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * xiecong
 */
@Component
public class Student {

	private String name;

	private String code;

	@Autowired
	private Tea tea;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", code='" + code + '\'' +
				'}';
	}
}
