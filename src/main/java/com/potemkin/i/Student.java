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
    @Value(referenceToValue = 2)
    private int age;

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

    public void setName(@Value String name) {
        this.name = name;
    }
}
