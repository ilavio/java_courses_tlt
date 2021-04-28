package com.potemkin.i.chat;

import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс NWriterThread источник сообщений
 * 
 * @author Илья Пот
 */
@Slf4j
public class NWriterThread implements Runnable {
    private int time = 0;
    private String massege = "Всем привет из NWriterThread!";
    private Exchanger<String> exchanger;

    /**
     * Конструктор класса NWriterThread
     * 
     * @param exchanger - принимаемый объект для синхронизации передачи данных
     */
    public NWriterThread(Exchanger<String> exchanger) {
        Random random = new Random();
        this.time = random.nextInt(10);
        this.exchanger = exchanger;
    }

    /**
     * Метод смены сообщений
     * 
     * @param massege - принимаемая строка для смены сообщения
     */
    public void setMassege(String massege) {
        this.massege = massege;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String stringEmpaty = exchanger.exchange(massege);
                log.info("Ожидайте {}{}", time, "с");
                TimeUnit.SECONDS.sleep(time);
            }
        } catch (InterruptedException e) {
            log.error("Ошибка run(): {}", e);
        }
    }
}
