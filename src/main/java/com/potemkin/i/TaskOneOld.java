package com.potemkin.i;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс TaskOneOld генерации UUID и поиска конца света
 * 
 * @author Илья Пот
 */
@Slf4j
public class TaskOneOld {
    private Path path = Paths.get("textUUID.txt");

    /**
     * Метод запуска генерации списка UUID и поиска дат
     */
    public void generationStart() {
        String uuidStringList = printList(createUuid());
        writeFile(uuidStringList);
        List<String> listDate = lightDate(readFile());
        for (String date : listDate) {
            log.info("generationStart():\n {}", date);
        }
    }

    /**
     * Метод создания списка UUID
     * 
     * @return - список типа UUID
     */
    private List<UUID> createUuid() {
        List<UUID> list = new ArrayList<UUID>();// 10000
        for (int i = 0; i < 10000; i++) {
            list.add(UUID.randomUUID());
        }
        return list;
    }

    /**
     * Вывод списка в лог (в консоль)
     * 
     * @param list - типа UUID
     * @return - String списка
     */
    private String printList(List<UUID> list) {
        StringBuffer buffer = new StringBuffer();
        log.trace("printList():");
        for (UUID lis : list) {
            buffer.append(lis.toString());
            buffer.append("\n");
            log.trace("{}", lis);
        }
        return buffer.toString();
    }

    /**
     * Метод записи в файл textUUID.txt
     * 
     * @param uuid - элемент типа String
     */
    private void writeFile(String uuid) {
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
        List<String> list = new ArrayList<String>();
        List<String> listFilter = new ArrayList<String>();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            while (bufferedReader.ready()) {
                list.add(bufferedReader.readLine());
            }
            for (int i = 0; i < list.size(); i++) {
                if (checkingNumber(list.get(i))) {
                    listFilter.add(list.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i = 0;
        StringBuffer stringBuf = new StringBuffer();
        writeFile("\n UUID сумма чисел которых больше 100: \n");
        for (String str : listFilter) {
            i++;
            log.trace("{}) {}", i, str);
            stringBuf.append(i + ") " + str + "\n");
        }
        stringBuf.append("\n");
        writeFile(stringBuf.toString());
        return listFilter;
    }

    /**
     * Метод проверки UUID элемента на суммучисел более 100
     * 
     * @param element - String
     * @return если больше true иначе false
     */
    private boolean checkingNumber(String element) {
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
        List<String> listDate = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            listDate.add(formatDate(list.get(i)));
        }
        return listDate;
    }

    /**
     * Метод преоброзования UUID в дату типа String
     * 
     * @param element - тапа String c UUID
     * @return - String даты
     */
    private String formatDate(String element) {
        ZoneId zone = ZoneId.of("Pacific/Guadalcanal");
        LocalDateTime localDate = LocalDateTime.now(zone);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        int[] mas = withdrawalNumbers(element);
        int day = mas[0] * 10 + mas[1];
        int month = mas[2] * 10 + mas[3];
        localDate = localDate.plusDays(day);
        localDate = localDate.plusMonths(month);
        return localDate.format(dateTimeFormatter);
    }

    /**
     * Метод изъятия чисел из UUID
     * 
     * @param element - тапа String c UUID
     * @return массив чисел int из UUID
     */
    private int[] withdrawalNumbers(String element) {
        String[] mas = element.split("[^0-9]()|()");
        int i = 0;
        for (String str : mas) {
            if (!str.equals("")) {
                i = i + 1;
            }
        }
        int[] integer = new int[i];
        i = 0;
        for (String str : mas) {
            if (!str.equals("")) {
                integer[i] = Integer.parseInt(str);
                i = +1;
            }
        }
        return integer;
    }
}
