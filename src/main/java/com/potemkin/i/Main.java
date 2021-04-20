package com.potemkin.i;

import java.util.Random;

import com.potemkin.i.exception.MyIndexOutOfBoundException;
import com.potemkin.i.exception.MyNoSuchFieldException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        CacheElement<Character> cachE1 = new CacheElement<Character>(1, 'a');
        CacheElement<Character> cachE2 = new CacheElement<Character>(1, 'a');
        log.info("{}{}{}", cachE1.equals(cachE2), ", ", cachE1);
        Cache<String> cashe = new Cache<String>(10);
        Random random = new Random();
        log.info("Вывод: {}", cashe);
        for (int i = 0; i < 10; i++) {
            cashe.add(Character.toString(random.nextInt(25) + 'a'), i);
        }
        log.info("Вывод: {}", cashe);
        char charTest = (char) (random.nextInt(25) + 'a');
        cashe.add(Character.toString(charTest), 10);
        cashe.add(Character.toString('a'), 21);
        log.info("Получение Cache элемента через get: {}", cashe.get(Character.toString(charTest)));
        log.info("Содержимое cache: {}", cashe);
        cashe.delete(Character.toString(charTest));
        log.info("После удаления элемента:{} :{}", charTest, cashe);
        cashe.clear();
        log.info("После очистки clear:{}{}", cashe, "\n");
        Storage<Integer> stor = new Storage<Integer>();
        log.info("Создали пустой (Стандартный) Storage:{}. Наполняем:", stor);
        for (int x = 0; x < 20; x++) {
            stor.add(random.nextInt(30));
        }
        log.info("Вывод: {}", stor);
        for (int i = 20; i >= -2; i -= 1) {
            try {
                stor.get(i);
            } catch (MyIndexOutOfBoundException e) {
                log.debug("Вышли за предел списка:", e);
            }
        }
        stor.delete();
        stor.delete();
        log.info("Проверка логики удаления delete:{}", stor);
        stor.delete();
        log.info("Вызов последнего элемента getLast(): {}", stor.getLast());
        stor.clear();
        log.info("После очистки clear:{}", stor);
        CallChekedException cheked = new CallChekedException(cashe);
        try {
            cheked.fieldCache();
        } catch (MyNoSuchFieldException e) {
            log.debug(new MyNoSuchFieldException().getMessage());
        }
    }
}
