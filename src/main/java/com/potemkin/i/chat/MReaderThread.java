package com.potemkin.i.chat;

import java.util.List;
import java.util.Random;

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
    private Random random = new Random();

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
            log.info("MReaderThread Получил сообщение: {}", read(massege.getListMassage()));
            log.info("MReaderThread Следующее чтение через {}{}", time, "с");
        }
    }

    /**
     * Метод чтения сообщений
     * 
     * @param massegeList - список не прочитанных сообщений
     * @return прочитанное сообщение типа String
     */
    public String read(List<String> massegeList) {
        var masIndex = random.nextInt(massegeList.size());
        return massegeList.remove(masIndex);
    }

}
