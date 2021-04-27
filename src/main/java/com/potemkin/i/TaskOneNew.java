package com.potemkin.i;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс TaskOneNew генерации UUID и поиска конца света
 * 
 * @author Илья Пот
 */
public class TaskOneNew {
    private static final Logger log = LoggerFactory.getLogger(TaskOneNew.class);
    private Path path = Paths.get("textUUIDNew.txt");

    /**
     * Метод запуска генерации списка UUID и поиска дат
     */
    public void generationStart() {
        log.info("generationStart(): \n");
        StringBuffer strBuf = new StringBuffer();
        for (UUID element : createUuidList()) {
            strBuf.append(element.toString() + "\n");
        }
        writeFile(strBuf.toString());
        for (String date : lightDate(readFile())) {
            log.info(date);
        }
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    /**
     * Метод создания списка UUID
     * 
     * @return - список типа UUID
     */
    private List<UUID> createUuidList() {
        log.trace("createUuidList(): ");
        List<UUID> list = Stream.generate(() -> UUID.randomUUID()).limit(10000)
                .collect(Collectors.toCollection(ArrayList<UUID>::new));
        for (UUID element : list) {
            log.trace(element.toString());
        }
        return list;
    }

    /**
     * Метод записи в файл textUUIDNew.txt
     * 
     * @param uuid - элемент типа String
     */
    private void writeFile(String uuid) {
        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                log.error("Ошибка writeFile(): ", e);
            }
        }
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                log.error("Ошибка writeFile(): ", e);
            }
        }
        try (BufferedOutputStream bufferedWriter = new BufferedOutputStream(
                Files.newOutputStream(path, StandardOpenOption.APPEND))) {
            bufferedWriter.write(uuid.getBytes());
            bufferedWriter.flush();
        } catch (IOException e) {
            log.error("Ошибка writeFile(): ", e);
        }
    }

    /**
     * Метод чтения из файла UUID список
     * 
     * @return List типа String отфильтрованных UUID
     */
    private List<String> readFile() {
        List<String> list = null;
        try (Stream<String> lineStream = Files.lines(path)) {
            list = lineStream.parallel().filter(element -> {
                return CheckingNumber(element);
            }).collect(Collectors.toCollection(ArrayList<String>::new));
        } catch (IOException e) {
            log.error("Ошибка readFile(): ", e);
        }
        int count = list.size();
        StringBuffer stringBuf = new StringBuffer();
        stringBuf.append("\n UUID сумма чисел которых больше 100: \n");
        stringBuf.append(count);
        stringBuf.append("\n");
        writeFile(stringBuf.toString());
        return list;
    }

    /**
     * Метод преоброзования UUID в массив чисел (изъятие чисел)
     * 
     * @param element - элемент из списка UUID
     * @return - массив int
     */
    private int[] withdrawalNumbers(String element) {
        return Arrays.stream(element.split("[^0-9]()|()")).filter(el -> !el.equals(""))
                .mapToInt(el -> Integer.parseInt(el)).toArray();
    }

    /**
     * Метод проверки UUID элемента на суммучисел более 100
     * 
     * @param element
     * @return если больше true иначе false
     */
    private boolean CheckingNumber(String element) {
        String[] mas = element.split("[^0-9]()|()");
        int i = 0;
        for (String str : mas) {
            if (!str.equals("")) {
                i = i + Integer.parseInt(str);
            }
        }
        log.trace("{} = i > 100 - {}", i, (i > 100));
        return i > 100;
    }

    /**
     * Метод поиска даты (преоброзования) из списка
     * 
     * @param list - тапа String c UUID
     * @return - список с датами типа String
     */
    private List<String> lightDate(List<String> list) {
        ZoneId zone = ZoneId.of("Pacific/Guadalcanal");
        LocalDateTime localDate = LocalDateTime.now(zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        List<String> listDate = list.stream().map(element -> {
            int[] mas = withdrawalNumbers(element);
            int day = mas[0] * 10 + mas[1];
            int month = mas[2] * 10 + mas[3];
            LocalDateTime localDate1 = localDate.plusDays(day);
            LocalDateTime localDate2 = localDate1.plusMonths(month);
            return localDate2.format(dateTimeFormatter);
        }).collect(Collectors.toCollection(ArrayList::new));
        return listDate;
    }
}
