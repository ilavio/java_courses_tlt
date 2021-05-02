package com.potemkin.i;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс SausageProcessor обработки колбасс
 * 
 * @author Илья Пот
 */
@Slf4j
public class SausageProcessor {
    private Path path = Paths.get("src/main/resources/file.txt");

    /**
     * Метод чтения из файла и создания листа с объектами Sausage
     */
    public void readFileCreateSausage() {
        StringBuffer buffer = new StringBuffer();
        log.info("readFileCreateSausage():");
        try (Stream<String> lineStream = Files.lines(path)) {
            List<Sausage> list = lineStream.map(element -> decoding(element)).map(element -> transformation(element))
                    .collect(Collectors.toList());
            for(Sausage element : list) {
                buffer.append(element.toString());
                buffer.append("\n");
            }
            log.info("{}", buffer);
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
}
