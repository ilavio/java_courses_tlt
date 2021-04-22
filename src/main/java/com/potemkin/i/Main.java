package com.potemkin.i;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.potemkin.i.annotations.ClassAnalyzer;

public class Main {
    static final Logger logRoot = LogManager.getRootLogger();

    public static void main(String[] args) {
        Human human = new Human();
        Student student = new Student();
        logRoot.info("Запуск Human <----------------------------------------------------->");
        ClassAnalyzer.start(human);
        logRoot.info("Human age:{} , Name: {}", human.getAge(), human.getName());
        logRoot.info("Запуск Student <----------------------------------------------------->");
        ClassAnalyzer.start(student);
        logRoot.info("Student age:{} , Name: {}", student.getAge(), student.getName());
    }
}
