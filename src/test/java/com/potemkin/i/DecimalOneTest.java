package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тестирование класса DecimalOne
 * 
 * @author Илья Пот
 */
public class DecimalOneTest {
    @Test
    public void testCreateMonkeyTest() {
        DecimalOne decimalOne = new DecimalOne();
        assertNotNull(decimalOne);
    }

    @Test
    public void testSetHuman() {
        double decimal = 102.23;
        DecimalOne decimalOne = new DecimalOne();
        decimalOne.setDecimal(102.23);
        assertEquals(decimalOne.getDecimal(), decimal);
    }
}
