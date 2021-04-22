package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HumanTest {
    
    @Test
    public void testCreateHuman() {
        Human human = new Human();
        assertNotNull(human);
    }
    
    @Test
    public void testSetHuman() {
        int age = 11;
        String name = "Алексей";
        Human human = new Human();
        human.setAge(11);
        human.setName("Алексей");
        assertEquals(human.getAge(), age);
        assertEquals(human.getName(), name);
    }
}
