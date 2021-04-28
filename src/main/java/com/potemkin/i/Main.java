package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс пуска демонстрации Race и Deadlock
 * 
 * @author Илья Пот
 */
@Slf4j
public class Main {
    private static ThreadRace race1 = new ThreadRace();
    private static ThreadRace race2 = new ThreadRace();
    private static ThreadRace race3 = new ThreadRace();
    private static ThreadWithSynch threadSynch1 = new ThreadWithSynch();
    private static ThreadWithSynch threadSynch2 = new ThreadWithSynch();
    private static ThreadWithSynch threadSynch3 = new ThreadWithSynch();
    private static ThreadDeadOne deadOne = new ThreadDeadOne();
    private static ThreadDeadTwo deadTwo = new ThreadDeadTwo();
    public static final Object DEADLOCKONE = new Object();
    public static final Object DEADLOCKTWO = new Object();

    public static void main(String[] args) {
        log.trace("Пуск потоков для не синхронизированного подсчета (Race): ");
        Thread myThread1 = new Thread(race1, "myThread1");
        Thread myThread2 = new Thread(race2, "myThread2");
        Thread myThread3 = new Thread(race3, "myThread3");
        myThread1.start();
        myThread2.start();
        myThread3.start();
        try {
            myThread1.join();
            myThread2.join();
            myThread3.join();
        } catch (InterruptedException e) {
            log.error("Ошибка main(): ", e);
        }
        log.info("{}{}{}", myThread1.getName(), ": ", race1.getStrBuf().toString());
        log.info("{}{}{}", myThread2.getName(), ": ", race2.getStrBuf().toString());
        log.info("{}{}{}", myThread3.getName(), ": ", race3.getStrBuf().toString());
        log.info("Без синхронизации итог: " + SourceForThread.getStrBufMain().toString());
        log.trace("Пуск потоков для синхронизированного подсчета: ");
        Thread myThreadSynch1 = new Thread(threadSynch1, "threadSynch1");
        Thread myThreadSynch2 = new Thread(threadSynch2, "threadSynch2");
        Thread myThreadSynch3 = new Thread(threadSynch3, "threadSynch3");
        myThreadSynch1.start();
        myThreadSynch2.start();
        myThreadSynch3.start();
        try {
            myThreadSynch1.join();
            myThreadSynch2.join();
            myThreadSynch3.join();
        } catch (InterruptedException e) {
            log.error("Ошибка main(): ", e);
        }
        log.info("{}{}{}", myThreadSynch1.getName(), ": ", threadSynch1.getStrBuf().toString());
        log.info("{}{}{}", myThreadSynch2.getName(), ": ", threadSynch2.getStrBuf().toString());
        log.info("{}{}{}", myThreadSynch3.getName(), ": ", threadSynch3.getStrBuf().toString());
        log.info("C синхронизацией итог: {}", SourceForThread.getStrBufMainSynch().toString());
        log.trace("Пуск потоков для взаимного захвата объектов (Deadlock)");
        Thread myThreadDeadOne = new Thread(deadOne);
        Thread myThreadDeadTwo = new Thread(deadTwo);
        myThreadDeadOne.start();
        myThreadDeadTwo.start();
    }

}
