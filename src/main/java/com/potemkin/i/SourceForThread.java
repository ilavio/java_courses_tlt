package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс SourceForThread источник для потоков
 * 
 * @author Илья Пот
 *
 */
@Slf4j
public class SourceForThread {
    private static int count = 0;
    private static StringBuffer strBufMain = new StringBuffer();
    private static int synchCount = 0;
    private static StringBuffer strBufMainSynch = new StringBuffer();

    /**
     * Метод не синхронизированный подсчета числа
     * 
     * @param count - число, шаг суммирования
     * @return - сумма чисел
     */
    public static int setCount(int count) {
        SourceForThread.count += count;
        strBufMain.append(SourceForThread.count + "; ");
        return SourceForThread.count;
    }

    public static int getCount() {
        return count;
    }

    /**
     * Метод синхронизированный подсчета числа
     * 
     * @param count - число, шаг суммирования
     * @return - сумма чисел
     */
    public static synchronized int countThreadsynchronized(int count) {
        SourceForThread.synchCount += count;
        strBufMainSynch.append(SourceForThread.synchCount + "; ");
        return SourceForThread.synchCount;
    }

    public static int getSynchCount() {
        return synchCount;
    }

    public static StringBuffer getStrBufMain() {
        return strBufMain;
    }

    public static StringBuffer getStrBufMainSynch() {
        return strBufMainSynch;
    }
}
