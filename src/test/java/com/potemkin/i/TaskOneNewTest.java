package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Класс тестирования TaskOneNew
 * 
 * @author Илья Пот
 */
public class TaskOneNewTest {
    @Test
    public void checkingTaskOneNewNotNull() {
        TaskOneNew taskOneNew = new TaskOneNew();
        taskOneNew.generationStart();
        assertNotNull(taskOneNew);
    }
}
