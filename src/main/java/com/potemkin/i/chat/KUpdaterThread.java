package com.potemkin.i.chat;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс KUpdaterThread обновления сообщений
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class KUpdaterThread implements Runnable {
    private List<String> massegeList;
    private int time;
    private Exchanger<List<String>> exchanger;

    /**
     * Конструктор класса KUpdaterThread
     * 
     * @param exchanger - принимаемый объект для синхронизации передачи данных
     */
    public KUpdaterThread(Exchanger<List<String>> exchanger) {
        Random ran = new Random();
        this.exchanger = exchanger;
        this.time = ran.nextInt(20);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                massegeList = exchanger.exchange(massegeList);
                log.info("Изменненое сообщение: {}", transformation(massegeList));
                TimeUnit.SECONDS.sleep(time);
                log.info("KUpdaterThread Ожидайте {}{}", time, "с");
            } catch (InterruptedException e) {
                log.error("Ошибка run(): {}", e);
            }
        }
    }

    /**
     * Метод трансформации (изменения) сообщения
     * 
     * @param massegeList - список сообщений
     * @return Сообщение измененное типа String
     */
    private String transformation(List<String> massegeList) {
        Random random = new Random();
        StringBuffer strBuf = new StringBuffer();
        int masIndex = random.nextInt(massegeList.size());
        String[] masWorld = massegeList.get(masIndex).split(" ");
        int xWorld = random.nextInt(masWorld.length);
        for (int i = 0; i < masWorld.length; i++) {
            if (i < xWorld || xWorld < i) {
                strBuf.append(masWorld[i] + " ");
            }
        }
        massegeList.set(masIndex, strBuf.toString());
        return strBuf.toString();
    }
}
