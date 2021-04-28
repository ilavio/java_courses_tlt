package com.potemkin.i.chat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
        Exchanger<String> exchanger = new Exchanger<String>();
        Exchanger<String> exchangerR = new Exchanger<String>();
        Exchanger<List<String>> exchangerU = new Exchanger<List<String>>();
        List<String> listMassage = new ArrayList<String>();
        boolean activ = true;
        NWriterThread writer = new NWriterThread(exchanger);
        MReaderThread reader = new MReaderThread(exchangerR);
        KUpdaterThread updater = new KUpdaterThread(exchangerU);
        Thread myThread1 = new Thread(writer);
        Thread myThread2 = new Thread(reader);
        Thread myThread3 = new Thread(updater);
        myThread1.start();
        myThread2.start();
        myThread3.start();
        String massege = "";
        while (activ) {
            try {
                if (massege.equals("")) {
                    massege = exchanger.exchange(massege);
                    log.trace("Сообщение: {}", massege);
                }
                log.info("Получили: {}", massege);
                if (massege != null) {
                    listMassage.add(massege);
                }
                massege = exchangerR.exchange(massege, 2, TimeUnit.SECONDS);
                listMassage = exchangerU.exchange(listMassage, 2, TimeUnit.SECONDS);
            } catch (InterruptedException | TimeoutException e) {
                log.error("Ошибка main(): {}", e);
            }
            activ = false;
        }
    }

}
