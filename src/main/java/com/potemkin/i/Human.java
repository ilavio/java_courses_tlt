package com.potemkin.i;

import com.potemkin.i.annotations.Entity;
import com.potemkin.i.annotations.Value;

@Entity
public class Human {
    @Value(age = 18)
    private int age;

    @Value(name = "ggg")
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(@Value(age = 34) int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(@Value(name = "Foma") String name) {
        this.name = name;
    }
}
