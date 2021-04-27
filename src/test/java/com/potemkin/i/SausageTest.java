package com.potemkin.i;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Класс тестирования Sausage
 * 
 * @author Илья Пот
 */
public class SausageTest {
    Sausage sausage = new Sausage();
    
    @Test
    public void checkingSausageReturnEqual() {
        Sausage sausageTwo = new Sausage();
        sausage.setType("Краковская");
        sausage.setCost(1200);
        sausage.setWeight(1300);
        sausageTwo.setType("Краковская");
        sausageTwo.setCost(1200);
        sausageTwo.setWeight(1300);
        assertEquals(sausageTwo.getType(), sausage.getType());
        assertEquals(sausageTwo.getCost(),sausage.getCost());
        assertEquals(sausageTwo.getWeight(), sausage.getWeight());
    }
}
