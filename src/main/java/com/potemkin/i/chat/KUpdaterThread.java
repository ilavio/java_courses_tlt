package com.potemkin.i.chat;

import java.util.List;
import java.util.Random;
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
    private int time;
    private Random random;
    private Message message;

    /**
     * Конструктор класса KUpdaterThread
     * 
     * @param message - принимаемый объект для синхронизации передачи данных
     * @param time    - время задержки
     */
    public KUpdaterThread(Message message, int time) {
        random = new Random();
        this.time = time;
        this.message = message;
    }

    @Override
    public void run() {
        var updateSms = transformation(message.getListMassage());
        log.info("Изменненое сообщение: {}", updateSms);
        log.info("KUpdaterThread Следующее изменение через {}{}", time, "с");
    }

    /**
     * Метод трансформации (изменения) сообщения
     * 
     * @param massegeList - список сообщений
     * @return Сообщение измененное типа String
     */
    private String transformation(List<String> massegeList) {
        StringBuffer strBuf = new StringBuffer();
        int masIndex = random.nextInt(massegeList.size());
        log.info("transformation() Выбранное сообщение: {}, {}", masIndex, massegeList.get(masIndex));
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
