package com.potemkin.i.chat;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс чтения сообщений MReaderThread
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class MReaderThread implements Runnable {
    private String massege = "";
    private int time;
    private Exchanger<String> exchanger;

    /**
     * Конструктор класса MReaderThread
     * 
     * @param exchanger - принимаемый объект для синхронизации передачи данных
     */
    public MReaderThread(Exchanger<String> exchanger) {
        Random random = new Random(20);
        this.time = 6;
        random.nextInt(60);
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                massege = exchanger.exchange(massege);
                log.info("MReaderThread Получил сообщение: {}", massege);
                log.info("MReaderThread Ожидайте {}{}", time, "с");
                TimeUnit.SECONDS.sleep(time);
            }
        } catch (InterruptedException e) {
            log.error("Ошибка run(): {}", e);
        }

    }

}
