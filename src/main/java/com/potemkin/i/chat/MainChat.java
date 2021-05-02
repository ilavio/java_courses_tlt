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
        Random random = new Random(20);
        int secondTimeN = random.nextInt(60);
        int secondTimeM = random.nextInt(50);
        int secondTimeK = random.nextInt(20);
        int limitMessage = 25;
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        Message message = new Message();
        NWriterThread writer = new NWriterThread(message, secondTimeN);
        MReaderThread reader = new MReaderThread(message, secondTimeM);
        KUpdaterThread updater = new KUpdaterThread(message, secondTimeK);
        scheduler.scheduleWithFixedDelay(updater, 0, secondTimeK, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(reader, 2, secondTimeM, TimeUnit.SECONDS);
        scheduler.scheduleWithFixedDelay(writer, 0, secondTimeN, TimeUnit.SECONDS);
        while (true) {
            if (message.getListMassage().size() > limitMessage) {
                scheduler.shutdown();
                break;
            }
        }
    }

}
