package com.potemkin.i.exception;

public class NoValueAnnotationException extends Exception {
    public NoValueAnnotationException() {
        super("Отсутствует значение поля");
    }
}
