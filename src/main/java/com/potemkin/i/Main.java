package com.potemkin.i;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

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
