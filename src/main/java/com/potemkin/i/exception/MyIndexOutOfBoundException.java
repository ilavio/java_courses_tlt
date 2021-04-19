/*
 * Все права защищены.
 * Регулируется лицензией Epam.
 * 
 */
package com.potemkin.i.exception;

/**
 * Класс исключения unchecked MyIndexOutOfBoundException в случае выхода за
 * пределы массива, который может быть выброшен во время нормальной работы.
 * Непроверенные исключения не должны объявляться в методе или конструкторе.
 * Предложение может быть вызвано выполнением метода или конструктора и
 * распространяться за пределы метода или конструктора.
 * 
 * @author Илья Пот
 * @since 1.0
 */
public class MyIndexOutOfBoundException extends RuntimeException {

    /**
     * Дефолтный конструктор класса Создает новое исключение с со стандартным
     * сообщением.
     */
    public MyIndexOutOfBoundException() {
        super("Вы вышли за пределы массива!");
    }
}
