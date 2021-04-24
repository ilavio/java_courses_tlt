package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Тестирование класса StudentTest
 * 
 * @author Илья Пот
 */
public class StudentTest {
    @Test
    public void testCreateStudent() {
        Student student = new Student();
        assertNotNull(student);
    }

    @Test
    public void testSetHuman() {
        int age = 11;
        String name = "Воробей";
        Student student = new Student();
        student.setAge(11);
        student.setName("Воробей");
        assertEquals(student.getAge(), age);
        assertEquals(student.getName(), name);
    }
}
