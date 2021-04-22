package com.potemkin.i.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Указывает, что объявление поля класса и аргумента метода предназначено для
 * внедрения значений
 * 
 * @author Илья Пот
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface Value {
    int age() default 0;

    String name() default "no name";

    int referenceToValue() default 0;
}
