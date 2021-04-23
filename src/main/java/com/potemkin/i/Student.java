package com.potemkin.i;

import com.potemkin.i.annotations.Entity;
import com.potemkin.i.annotations.Value;

/**
 * Класс POJO Student
 * 
 * @author Илья Пот
 */
@Entity
public class Student {
    @Value(referenceToValue = "age3")
    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    @Value(value = "34")
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Value
    public void setName(String name) {
        this.name = name;
    }
}
