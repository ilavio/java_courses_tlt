package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс ThreadDeadTwo двойного захвата объектов в монитор
 * 
 * @author Илья Пот
 */
@Slf4j
public class ThreadDeadTwo implements Runnable {

    @Override
    public void run() {
        log.info("ThreadDeadTwo Захват потока");
        synchronized (Main.DEADLOCKTWO) {
            log.info("ThreadDeadTwo Поток захвачен DEADLOCKTWO");
            log.info("ThreadDeadTwo Захват потока");
            synchronized (Main.DEADLOCKONE) {
                log.info("ThreadDeadTwo Поток захвачен DEADLOCKONE");
            }
        }
    }
}
