package com.potemkin.i.chat;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс обработки сообщений MessageHandler
 * 
 * @author Илья Пот
 */
@Slf4j
public class MessageHandler {
    private Random random = new Random(20);
    private int secondTimeN = random.nextInt(60);
    private int secondTimeM = random.nextInt(50);
    private int secondTimeK = random.nextInt(20);
    private int limitMessage = 25;
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
    private Message message = new Message();
    private NWriterThread writer = new NWriterThread(message, secondTimeN);
    private MReaderThread reader = new MReaderThread(message, secondTimeM);
    private KUpdaterThread updater = new KUpdaterThread(message, secondTimeK);
    private Thread write = new Thread(writer, "write");

    /**
     * Метод запуска чата
     */
    public void start() {
        while (true) {
            if (message.getListMassage().size() == 0) {
                write.start();
                scheduler.scheduleWithFixedDelay(reader, 1, secondTimeM, TimeUnit.SECONDS);
                scheduler.scheduleWithFixedDelay(updater, 2, secondTimeK, TimeUnit.SECONDS);
            }
            if (message.getListMassage().size() == limitMessage) {
                try {
                    write.wait();
                } catch (InterruptedException e) {
                    log.error("MessageHandler Ошибка start(): ", e);
                }
            }
            if (message.getListMassage().size() < limitMessage && message.getListMassage().size() > limitMessage - 2) {
                try {
                    write.notify();
                } catch (Exception e) {
                    log.error("MessageHandler Ошибка start(): ", e);
                }
            }
        }
    }
}
