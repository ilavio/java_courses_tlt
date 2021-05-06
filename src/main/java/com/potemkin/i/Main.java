package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс пуска демонстрации Race и Deadlock
 * 
 * @author Илья Пот
 */
@Slf4j
public class Main {
    public static final Object DEADLOCKONE = new Object();
    public static final Object DEADLOCKTWO = new Object();

    public static void main(String[] args) {
        startWithoutSynchronization();
        synchronizedStart();
        captureOfObjects();
        mutualCaptureOfObjects();
    }

    /**
     * Метод запуска потоков для не синхронизированного подсчета
     */
    public static void startWithoutSynchronization() {
        var race1 = new ThreadRace();
        var race2 = new ThreadRace();
        var race3 = new ThreadRace();
        log.trace("Пуск потоков для не синхронизированного подсчета (Race): ");
        var myThread1 = new Thread(race1, "myThread1");
        var myThread2 = new Thread(race2, "myThread2");
        var myThread3 = new Thread(race3, "myThread3");
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
    }

    /**
     * Метод запуска потоков с синхронизацией
     */
    public static void synchronizedStart() {
        var threadSynch1 = new ThreadWithSynch();
        var threadSynch2 = new ThreadWithSynch();
        var threadSynch3 = new ThreadWithSynch();
        log.trace("Пуск потоков для синхронизированного подсчета: ");
        var myThreadSynch1 = new Thread(threadSynch1, "threadSynch1");
        var myThreadSynch2 = new Thread(threadSynch2, "threadSynch2");
        var myThreadSynch3 = new Thread(threadSynch3, "threadSynch3");
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
    }

    /**
     * Метод взаимного захвата объекта для Deadlock
     */
    public static void mutualCaptureOfObjects() {
        var deadOne = new ThreadDeadOne();
        var deadTwo = new ThreadDeadTwo();
        log.trace("Пуск потоков для взаимного захвата объектов (Deadlock)");
        var myThreadDeadOne = new Thread(deadOne);
        var myThreadDeadTwo = new Thread(deadTwo);
        myThreadDeadOne.start();
        myThreadDeadTwo.start();
    }
    
    /**
     * Метод захвата объектов по очереди без Deadlock
     */
    public static void captureOfObjects() {
        var withoutDeadlock = new ThreadWithoutDeadOne();
        var deadTwo = new ThreadDeadTwo();
        log.trace("Пуск потоков очередного захвата объектов (без Deadlock)");
        var myThreadDeadOne = new Thread(withoutDeadlock);
        var myThreadDeadTwo = new Thread(deadTwo);
        myThreadDeadOne.start();
        myThreadDeadTwo.start();
    }
}
