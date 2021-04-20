package com.potemkin.i;

import com.potemkin.i.annotations.ClassAnalyzer;

public class Main {
    public static void main(String []args) {
        Human human = new Human();
        Student student = new Student();
        ClassAnalyzer.annotationHandlerValueField(human);
        System.out.println(human.getAge() + "; " + human.getName());
        ClassAnalyzer.annotationHandlerValueHumanParametr(human);
        System.out.println(human.getAge() + "; " + human.getName());
        ClassAnalyzer.annotationHandlerValueField(student);
        System.out.println(student.getAge() + "; " + student.getName());
        System.out.println(ClassAnalyzer.initializationEntity(human));
        ClassAnalyzer.start(human);
        System.out.println(human.getAge() + "; " + human.getName());
    }
}
