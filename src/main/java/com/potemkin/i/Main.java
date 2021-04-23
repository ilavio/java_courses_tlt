package com.potemkin.i;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.potemkin.i.annotations.ClassAnalyzer;

/**
 * Main
 * 
 * @author Илья Пот
 */
public class Main {
    static final Logger logRoot = LogManager.getRootLogger();

    public static void main(String[] args) {
        ClassAnalyzer classAnalyzer = new ClassAnalyzer();
        Human human = new Human();
        Student student = new Student();
        logRoot.info("Запуск Student <----------------------------------------------------->");
        classAnalyzer.start(student);
        logRoot.info("Student age:{} , Name: {}", student.getAge(), student.getName());
        logRoot.info("Запуск Human <----------------------------------------------------->");
        classAnalyzer.start(human);
        logRoot.info("Human age:{} , Name: {}", human.getAge(), human.getName());
    }
}
