package com.potemkin.i;

import java.lang.reflect.Field;

import com.potemkin.i.exception.MyNoSuchFieldException;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс запроса поля из объекта типа Cache, тестовый класс для вызова
 * проверяемого исключения
 * 
 * @author Илья Пот
 */
@Slf4j
public class CallChekedException {
    private Cache<?> cache;

    /**
     * Конструктор класса ChekedException
     * 
     * @param cache
     */
    public CallChekedException(Cache<?> cache) {
        this.cache = cache;
    }

    /**
     * Метод запроса поля nextItem
     * 
     * @throws MyNoSuchFieldException
     * @throws NoSuchFieldException   - выбрасываемое исключение
     */
    public void fieldCache() throws MyNoSuchFieldException {
        Class example = cache.getClass();
        try {
            Field field = example.getDeclaredField("nextItem");
            field.setAccessible(true);
        } catch (NoSuchFieldException e) {
            log.debug("Отсутствует поле fieldCache(): ", e);
        } catch (SecurityException e) {
            log.debug("Безопасность fieldCache(): ", e);
        }
        throw new MyNoSuchFieldException();
    }
}
