package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Класс тестирования SausageProcessor
 * 
 * @author Илья Пот
 */
public class SausageProcessorTest {
    @Test
    public void checkingSausageProcessorNotNull() {
        SausageProcessor processor = new SausageProcessor();
        processor.readFileCreateSausage();
        assertNotNull(processor);
    }
}
