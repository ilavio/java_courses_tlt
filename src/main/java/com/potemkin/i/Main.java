package com.potemkin.i;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Запуск TaskOneOld <------------------------------------------------->");
        TaskOneOld taskOneOld = new TaskOneOld();
        taskOneOld.generationStart();
        log.info("Запуск TaskOneNew <------------------------------------------------->");
        TaskOneNew taskOneNew = new TaskOneNew();
        taskOneNew.generationStart();
        log.info("Запуск SausageProcessor <------------------------------------------------->");
        SausageProcessor processor = new SausageProcessor();
        processor.readFileCreateSausage();
    }

}
