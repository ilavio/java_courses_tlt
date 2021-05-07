package com.potemkin.i.chat;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс запуска чата MainChat
 * 
 * @author Илья Пот
 */
@Slf4j
public class MainChat {
    public static void main(String[] args) {
        log.info("Пуск Чат<---------------------------------------->");
        var messageHandler = new MessageHandler();
        messageHandler.start();
    }
}
