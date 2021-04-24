package com.potemkin.i;

import com.potemkin.i.annotations.Entity;
import com.potemkin.i.annotations.Value;

/**
 * Класс POJO Monkey
 * 
 * @author Илья Пот
 */
@Entity
public class Monkey {
    @Value(referenceToValue = "itisMonkey")
    private boolean itIsMonkey;
    
    

    public boolean getItIsMonkey() {
        return itIsMonkey;
    }

    public void setItIsMonkey(boolean itIsMonkey) {
        this.itIsMonkey = itIsMonkey;
    }

}
