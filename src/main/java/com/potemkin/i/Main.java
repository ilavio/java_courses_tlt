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
        DecimalOne decimalone = new DecimalOne();
        DecimalTwo decimalTwo = new DecimalTwo();
        Monkey monkey = new Monkey();
        Student student = new Student();
        logRoot.info("Запуск Student <----------------------------------------------------->");
        classAnalyzer.start(student);
        logRoot.info("Student age:{} , Name: {}, Boolean: {} Integer: {}", student.getAge(), student.getName(),
                student.getItIsHuman(), student.getInteger());
        logRoot.info("Запуск Human <----------------------------------------------------->");
        classAnalyzer.start(human);
        logRoot.info("Human age:{} , Name: {}", human.getAge(), human.getName());
        logRoot.info("Запуск DecimalOne <----------------------------------------------------->");
        classAnalyzer.start(decimalone);
        logRoot.info("DecimalOne decimalOne: {}", decimalone.getDecimal());
        logRoot.info("Запуск DecimalTwo <----------------------------------------------------->");
        classAnalyzer.start(decimalTwo);
        logRoot.info("DecimalTwo decimalTwo: {}", decimalTwo.getDecimalTwo());
        logRoot.info("Запуск Monkey <----------------------------------------------------->");
        classAnalyzer.start(monkey);
        logRoot.info("Monkey ItIsMonkey: {}", monkey.getItIsMonkey());

    }
}
