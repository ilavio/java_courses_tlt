package com.potemkin.i.chat;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс чтения сообщений MReaderThread
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class MReaderThread implements Runnable {
    private Message massege;
    private int time;

    /**
     * Конструктор класса MReaderThread
     * 
     * @param massege - принимаемый объект для синхронизации передачи данных
     * @param time    - время задержки
     */
    public MReaderThread(Message massege, int time) {
        this.time = time;
        this.massege = massege;
    }

    @Override
    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            log.info("MReaderThread Получил сообщение: {}", massege.getMassege());
            log.info("MReaderThread Следующее чтение через {}{}", time, "с");
        }
    }

}
