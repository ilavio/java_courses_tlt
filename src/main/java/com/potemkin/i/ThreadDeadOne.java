package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

/**
 * * Класс ThreadDeadOne двойного захвата объектов в монитор
 * 
 * @author Илья Пот
 */
@Slf4j
public class ThreadDeadOne implements Runnable {

    @Override
    public void run() {
        log.info("ThreadDeadOne Захват потока");
        synchronized (Main.DEADLOCKONE) {
            log.info("ThreadDeadOne Поток захвачен DEADLOCKONE");
            log.info("ThreadDeadOne Захват потока");
            synchronized (Main.DEADLOCKTWO) {
                log.info("ThreadDeadOne Поток захвачен DEADLOCKTWO");
            }
        }
    }
}
