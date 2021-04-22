package com.potemkin.i.annotations;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.potemkin.i.exception.NoValueAnnotationException;

/**
 * Класс для анализа аннотированных объектов
 * 
 * @author Илья Пот
 */
public class ClassAnalyzer {
    static final Logger log = LogManager.getLogger(ClassAnalyzer.class);
    private static String path = "src/main/resources/file.txt";

    /**
     * Метод запуска анализа объекта
     * 
     * @param object
     */
    public static void start(Object object) {
        if (initializationEntity(object)) {
            try {
                annotationHandlerValueField(object);
            } catch (NoValueAnnotationException e) {
                annotationHandlerValueParametr(object);
                log.debug("Ошибка: ", e);
            }
            annotationHandlerValueParametr(object);
        }
    }

    /**
     * метод смены пути до файла со значениями
     * 
     * @param path
     */
    public static void setPath(String path) {
        ClassAnalyzer.path = path;
    }

    /**
     * метод проверки аннотирования объекта
     * 
     * @param object
     * @return если анотирован вернет true иначе false
     */
    private static boolean initializationEntity(Object object) {
        return object.getClass().isAnnotationPresent(Entity.class);
    }

    /**
     * метод анализа полей объекта
     * 
     * @param object - принимаемый объект
     * @throws NoValueAnnotationException в случае отсутствия аннотированного поля
     */
    private static void annotationHandlerValueField(Object object) throws NoValueAnnotationException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Value.class)) {
                Value valueannotation = field.getAnnotation(Value.class);
                field.setAccessible(true);
                String valueStr = (String) valueannotation.name();
                int valueInt = (int) valueannotation.age();
                int referenceToValue = (int) valueannotation.referenceToValue();
                log.info("Значение полей: {}{}{}{}{}{}{}", valueStr, ", ", valueInt, "; это анотировано: ",
                        field.isAnnotationPresent(Value.class), " ссылка значения: ", referenceToValue);
                if (referenceToValue == 0) {
                    assignmentOfValuesFields(field, valueStr, valueInt, object);
                }
                if (referenceToValue > 0) {
                    assignmentOfValuesFields(field, valueStr, valueInt, object, splittingFileIntoValues(loadFileTxt()),
                            referenceToValue);
                }
            } else {
                throw new NoValueAnnotationException();
            }
        }
    }

    /**
     * метод анализа параметра (аргумента) метода объекта
     * 
     * @param object - принимаемый объект
     */
    private static void annotationHandlerValueParametr(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                if (parameter.isAnnotationPresent(Value.class)) {
                    Value valueannotation = parameter.getAnnotation(Value.class);
                    String valueStr = (String) valueannotation.name();
                    int valueInt = (int) valueannotation.age();
                    log.info("Значение параметров метода: {}{}{}{}", valueStr, ", ", valueInt, " ;");
                    if (!valueStr.equals("") && parameter.getName().equals("arg0")) {
                        try {
                            method.invoke(object, valueStr);
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            log.debug("Ошибка: ", e);
                        } catch (InvocationTargetException e) {
                            log.debug("Ошибка: ", e);
                        }
                    }
                    if (valueInt != 0) {
                        try {
                            method.invoke(object, (Integer) valueInt);
                        } catch (IllegalArgumentException e) {
                            log.debug("Ошибка: ", e);
                        } catch (IllegalAccessException e) {
                            log.debug("Ошибка: ", e);
                        } catch (InvocationTargetException e) {
                            log.debug("Ошибка: ", e);
                        }
                    }
                }
            }
        }
    }

    /**
     * метод загрузки файла со списком значений для дольнейшего внедрения в объект
     * 
     * @return - StringBuffer
     */
    private static StringBuffer loadFileTxt() {
        StringBuffer stringBuf = new StringBuffer();
        if (!path.equals(null) && !path.equals("")) {
            File file = new File(path);
            if (file.isFile()) {
                try (BufferedInputStream buf2 = new BufferedInputStream(new FileInputStream(file), 64)) {
                    byte[] bytemas = new byte[64];
                    while (buf2.available() != 0) {
                        bytemas = buf2.readAllBytes();
                        stringBuf.append(new String(bytemas));
                    }
                } catch (FileNotFoundException e) {
                    log.debug("Ошибка: ", e);
                } catch (IOException e) {
                    log.debug("Ошибка: ", e);
                }
                log.info("Получили файл: {}", stringBuf);
                return stringBuf;
            }
        }
        return stringBuf;
    }

    /**
     * метод разделения файла на значения
     * 
     * @param stringBuf - принимает для разделения на отдельные значения
     * @return - Map<Integer, String[]> с ключом для ссылки к значениям
     */
    private static Map<Integer, String[]> splittingFileIntoValues(StringBuffer stringBuf) {
        Map<Integer, String[]> treeMapVailues = new TreeMap<Integer, String[]>();
        String[] splitString = stringBuf.toString().split("(\\{)|(\\}\\W+)|(\\})");
        for (int i = 1; i < splitString.length; i++) {
            String[] splitStringValues = new String[2];
            String[] masString1 = splitString[i].split("\r|\n");
            for (int x = 0; x < masString1.length; x++) {
                String[] masString2 = masString1[x].split("=");
                if (masString2[0].equals("age")) {
                    splitStringValues[0] = masString2[1];
                }
                if (masString2[0].equals("name")) {
                    splitStringValues[1] = masString2[1];
                }
                masString2 = null;
            }
            masString1 = null;
            treeMapVailues.put(i, splitStringValues);
        }
        log.info("Получаем список значений:");
        for (Map.Entry<Integer, String[]> entry : treeMapVailues.entrySet()) {
            log.info("{}) Значения {};", entry.getKey(), Arrays.toString(entry.getValue()));
        }
        return treeMapVailues;
    }

    /**
     * метод непостредственного внедрения значений в объект через значение аннотаций
     * 
     * @param field    - поле внедрения
     * @param valueStr - строка для name
     * @param valueInt - строка для age
     * @param object   - объект для внедрения
     */
    private static void assignmentOfValuesFields(Field field, String valueStr, int valueInt, Object object) {
        if (!valueStr.equals("") && field.getName().equals("name")) {
            try {
                field.set(object, valueStr);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (valueInt != 0 && field.getName().equals("age")) {
            try {
                field.setInt(object, valueInt);
            } catch (IllegalArgumentException e) {
                log.debug("Ошибка: ", e);
            } catch (IllegalAccessException e) {
                log.debug("Ошибка: ", e);
            }
        }
    }

    /**
     * метод непостредственного внедрения значений в объект через ссылку (ключ) из
     * файла
     * 
     * @param field            - поле внедрения
     * @param valueStr         - строка для name
     * @param valueInt         - строка для age
     * @param object           - объект для внедрения
     * @param treeMapVailues   - для ссылки к значениям
     * @param referenceToValue - (ключ) ссылка
     */
    private static void assignmentOfValuesFields(Field field, String valueStr, int valueInt, Object object,
            Map<Integer, String[]> treeMapVailues, int referenceToValue) {
        if (referenceToValue <= treeMapVailues.size()) {
            if (!treeMapVailues.get((Integer) referenceToValue)[0].equals(null)) {
                valueInt = Integer.parseInt(treeMapVailues.get(referenceToValue)[0]);
            }
            if (!treeMapVailues.get((Integer) referenceToValue)[1].equals(null)) {
                valueStr = treeMapVailues.get(referenceToValue)[1];
            }
            if (!valueStr.equals("") && field.getName().equals("name")) {
                try {
                    field.set(object, valueStr);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.debug("Ошибка: ", e);
                }
            }
            if (valueInt != 0 && field.getName().equals("age")) {
                try {
                    field.setInt(object, valueInt);
                } catch (IllegalArgumentException e) {
                    log.debug("Ошибка: ", e);
                } catch (IllegalAccessException e) {
                    log.debug("Ошибка: ", e);
                }
            }
        }
    }
}
