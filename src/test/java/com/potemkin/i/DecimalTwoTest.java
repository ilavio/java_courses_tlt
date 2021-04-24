package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тестирование класса DecimalTwo
 * 
 * @author Илья Пот
 */
public class DecimalTwoTest {
    @Test
    public void testCreateMonkeyTest() {
        DecimalTwo decimalTwo = new DecimalTwo();
        assertNotNull(decimalTwo);
    }

    @Test
    public void testSetHuman() {
        double decimal = 102.23;
        DecimalTwo decimalTwo = new DecimalTwo();
        decimalTwo.setDecimalTwo(102.23);
        assertEquals(decimalTwo.getDecimalTwo(), decimal);
    }
}
