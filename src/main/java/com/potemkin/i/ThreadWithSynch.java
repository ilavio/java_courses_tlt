package com.potemkin.i;

/**
 * Класс ThreadWithSynch синхронизированного подсчета
 * 
 * @author Илья Пот
 *
 */
public class ThreadWithSynch implements Runnable {
    private StringBuffer strBuf = new StringBuffer();

    @Override
    public void run() {
        for (int i = 0; SourceForThread.getSynchCount() < 1000; i++) {
            SourceForThread.countThreadsynchronized(1);
            strBuf.append(SourceForThread.getSynchCount() + "; ");
        }
    }

    public StringBuffer getStrBuf() {
        return strBuf;
    }
}
