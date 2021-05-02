package com.potemkin.i.chat;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс NWriterThread источник сообщений
 * 
 * @author Илья Пот
 */
@Slf4j
public class NWriterThread implements Runnable {
    private int time = 0;
    private String sms = "Всем привет из NWriterThread!";
    private Message message;

    /**
     * Конструктор класса NWriterThread
     * 
     * @param exchanger - принимаемый объект для синхронизации передачи данных
     * @param time      - время задержки
     */
    public NWriterThread(Message message, int time) {
        this.time = time;
        this.message = message;
    }

    /**
     * Метод смены сообщений
     * 
     * @param massege - принимаемая строка для смены сообщения
     */
    public void setMassege(String sms) {
        this.sms = sms;
    }

    @Override
    public void run() {
        message.setMassege(sms);
        message.addListMassage(sms);
        log.info("NWriterThread Отправка: {}", sms);
        log.info("NWriterThread Следующее сообщение через {}{}", time, "с");
    }
}
