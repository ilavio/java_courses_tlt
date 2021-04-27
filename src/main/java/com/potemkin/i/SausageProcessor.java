package com.potemkin.i;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс SausageProcessor обработки колбасс
 * 
 * @author Илья Пот
 */
public class SausageProcessor {
    private static final Logger log = LoggerFactory.getLogger(SausageProcessor.class);
    private Path path = Paths.get("src/main/resources/file.txt");

    /**
     * Метод чтения из файла и создания листа с объектами Sausage
     */
    public void readFileCreateSausage() {
        log.info("\nreadFileCreateSausage():");
        try (Stream<String> lineStream = Files.lines(path)) {
            List<Sausage> list = lineStream.map(element -> {
                return decoding(element);
            }).map(element -> {
                return transformation(element);
            }).collect(Collectors.toCollection(ArrayList<Sausage>::new));
            printSausage(list);
        } catch (IOException e) {
           log.error("Ошибка readFileCreateSausage(): {}", e);
        }
    }

    /**
     * Метод декодирования файла в UTF_8
     * 
     * @param element - типа Base64
     * @return String - декодированная строка в UTF_8
     */
    private String decoding(String element) {
        byte[] bytes = Base64.getDecoder().decode(element);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Метод создания из стринг Sausage объектов
     * 
     * @param element - типа String
     * @return - объект Sausage
     */
    private Sausage transformation(String element) {
        String[] blank = element.split("[a-z]+=|[^А-Яа-я0-9]+");
        return new Sausage(blank[2], Integer.parseInt(blank[3]), Long.parseLong(blank[4]));
    }

    /**
     * Метод вывода в консоль
     * 
     * @param list - выводимый список
     */
    private void printSausage(List<Sausage> list) {
        list.stream().forEach(el -> log.info("printSausage(): {}", el));
    }
}
