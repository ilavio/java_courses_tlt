package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadWithoutDeadOne implements Runnable {

    @Override
    public void run() {
        log.info("ThreadDeadOne Захват потока");
        synchronized (Main.DEADLOCKTWO) {
            log.info("ThreadDeadOne Поток захвачен DEADLOCKTWO");
            log.info("ThreadDeadOne Захват потока");
            synchronized (Main.DEADLOCKONE) {
                log.info("ThreadDeadOne Поток захвачен DEADLOCKONE");
            }
        }
    }
}
