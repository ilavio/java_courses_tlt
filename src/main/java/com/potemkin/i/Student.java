package com.potemkin.i;

import com.potemkin.i.annotations.Value;

public class Student {
    private int age;
    
    @Value
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
