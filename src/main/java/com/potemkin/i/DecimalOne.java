package com.potemkin.i;

import com.potemkin.i.annotations.Entity;
import com.potemkin.i.annotations.Value;

/**
 * Класс POJO DecimalOne
 * 
 * @author Илья Пот
 */
@Entity
public class DecimalOne {
    @Value(referenceToValue = "decimal1")
    private double decimal;

    public double getDecimal() {
        return decimal;
    }

    public void setDecimal(double decimal) {
        this.decimal = decimal;
    }
}
