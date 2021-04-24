package com.potemkin.i.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Указывает, что класс маркирован
 * 
 * @author Илья Пот
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Entity {
}
