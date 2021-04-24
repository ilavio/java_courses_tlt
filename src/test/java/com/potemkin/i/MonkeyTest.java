package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тестирование класса Monkey
 * 
 * @author Илья Пот
 */
public class MonkeyTest {
    @Test
    public void testCreateMonkeyTest() {
        Monkey monkey = new Monkey();
        assertNotNull(monkey);
    }

    @Test
    public void testSetHuman() {
        boolean itIsMonkey = true;
        Monkey monkey = new Monkey();
        monkey.setItIsMonkey(true);
        assertEquals(monkey.getItIsMonkey(), itIsMonkey);
    }
}
