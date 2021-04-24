package com.potemkin.i;

import com.potemkin.i.annotations.Entity;
import com.potemkin.i.annotations.Value;

/**
 * Класс POJO Human
 * 
 * @author Илья Пот
 */
@Entity
public class Human {
    @Value("18")
    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    @Value("34")
    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    @Value(value = "Foma")
    public void setName(String name) {
        this.name = name;
    }
}
