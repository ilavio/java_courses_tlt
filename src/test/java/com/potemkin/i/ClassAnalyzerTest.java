package com.potemkin.i;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.annotations.ClassAnalyzer;
import com.potemkin.i.exception.NoValueAnnotationException;

/**
 * ClassAnalyzerTest
 * 
 * @author Илья Пот
 */
public class ClassAnalyzerTest {
    Student student = new Student();
    Human human = new Human();
    ClassAnalyzer classAnalyzer = new ClassAnalyzer();

    @BeforeEach
    public void castToSingleValue() {
        classAnalyzer.start(human);
        student.setAge(human.getAge());
        student.setName(human.getName());
    }

    @Test
    public void comparingObjects() {
        assertEquals(student.getName(), human.getName());
    }

    @Test
    public void checkingException() throws NoValueAnnotationException {
        Exception exception = assertThrows(NoValueAnnotationException.class, () -> {
            classAnalyzer.start(student);
            throw new NoValueAnnotationException();
        });
    }

    @Test
    public void checkingDefaultAnnotationValue() {
        classAnalyzer.start(student);
        String string = "no name";
        assertEquals(student.getName(), string);
    }
}
