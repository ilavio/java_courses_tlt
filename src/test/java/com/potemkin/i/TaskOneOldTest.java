package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Класс тестиррования TaskOneOld
 * 
 * @author Илья Пот
 */
public class TaskOneOldTest {
    @Test
    public void checkingTaskOneOldNotNull() {
        TaskOneOld taskOneOld = new TaskOneOld();
        taskOneOld.generationStart();
        assertNotNull(taskOneOld);
    }
}
