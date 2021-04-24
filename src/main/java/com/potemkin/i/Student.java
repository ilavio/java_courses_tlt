package com.potemkin.i;

import com.potemkin.i.annotations.Entity;
import com.potemkin.i.annotations.Value;

/**
 * Класс POJO Student
 * 
 * @author Илья Пот
 */
public class Student {
    @Value(referenceToValue = "age3")
    private int age;
    
    @Value("true")
    private boolean itIsHuman;
    
    @Value("2345")
    private Integer integer;

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

    @Value
    public void setName(String name) {
        this.name = name;
    }

    public boolean getItIsHuman() {
        return itIsHuman;
    }

    public void setItIsHuman(boolean itIsHuman) {
        this.itIsHuman = itIsHuman;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }
    
}
