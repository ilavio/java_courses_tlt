package com.potemkin.i.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Указывает, что объявление поля класса или метода предназначено для
 * внедрения значения
 * 
 * @author Илья Пот
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface Value {
    String value() default "no name";

    String referenceToValue() default "no reference";
}
