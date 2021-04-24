package com.potemkin.i;

import com.potemkin.i.annotations.Entity;
import com.potemkin.i.annotations.Value;

/**
 * Класс POJO DecimalTwo
 * 
 * @author Илья Пот
 */
@Entity
public class DecimalTwo {
    @Value("123.345")
    private double decimalTwo;

    public double getDecimalTwo() {
        return decimalTwo;
    }

    public void setDecimalTwo(double decimalTwo) {
        this.decimalTwo = decimalTwo;
    }

}
