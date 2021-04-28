package com.potemkin.i;

/**
 * Класс ThreadRace не синхронизированного подсчета, для демонстрации
 * асинхронной работы потоков
 * 
 * @author Илья Пот
 */
public class ThreadRace implements Runnable {
    private StringBuffer strBuf = new StringBuffer();

    @Override
    public void run() {
        for (int i = 0; SourceForThread.getCount() < 1000; i++) {
            SourceForThread.setCount(1);
            strBuf.append(SourceForThread.getCount() + "; ");
        }
    }

    public StringBuffer getStrBuf() {
        return strBuf;
    }
}
