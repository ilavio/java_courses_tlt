package com.potemkin.i.exception;

/**
 * Класс исключения NoValueAnnotationException в случае отсутствия аннотации над
 * полем класса
 * 
 * @author Илья Пот
 * @since 1.0
 */
public class NoValueAnnotationException extends Exception {
    public NoValueAnnotationException() {
        super("Отсутствует аннотация поля класса");
    }
}
