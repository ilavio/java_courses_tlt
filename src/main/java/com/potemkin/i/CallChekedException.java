package com.potemkin.i;

import java.lang.reflect.Field;

import com.potemkin.i.exception.MyNoSuchFieldException;

/**
 * Класс запроса поля из объекта типа Cache, тестовый класс для вызова
 * проверяемого исключения
 * 
 * @author Илья Пот
 */
public class CallChekedException {
    private Cache <?> cache;

    /**
     * Конструктор класса ChekedException
     * 
     * @param cache
     */
    public CallChekedException(Cache <?> cache) {
        this.cache = cache;
    }

    /**
     * Метод запроса поля nextItem
     * 
     * @return String - возврат имени поля (переменной)
     * @throws MyNoSuchFieldException 
     * @throws NoSuchFieldException - выбрасываемое исключение
     */
    public void fieldCache() throws MyNoSuchFieldException  {
        Class example = cache.getClass();
        try {
            Field field = example.getDeclaredField("nextItem");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
        } catch (SecurityException e) {
        }
        throw new MyNoSuchFieldException();
    }
}
