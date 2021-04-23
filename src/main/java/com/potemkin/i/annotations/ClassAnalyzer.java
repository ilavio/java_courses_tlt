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
    private String path = "src/main/resources/file.txt";

    /**
     * Метод запуска анализа объекта
     * 
     * @param object
     */
    public void start(Object object) {
        if (initializationEntity(object)) {
            annotationHandlerValueMethod(object);
            try {
                annotationHandlerValueField(object);
            } catch (NoValueAnnotationException e) {
                log.debug("Ошибка в annotationHandlerValueField : ", e);
            }
        }
    }

    /**
     * метод смены пути до файла со значениями
     * 
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * метод проверки аннотирования объекта
     * 
     * @param object
     * @return если анотирован вернет true иначе false
     */
    private boolean initializationEntity(Object object) {
        return object.getClass().isAnnotationPresent(Entity.class);
    }

    /**
     * метод анализа полей объекта
     * 
     * @param object - принимаемый объект
     * @throws NoValueAnnotationException в случае отсутствия аннотированного поля
     *                                    объекта
     */
    private void annotationHandlerValueField(Object object) throws NoValueAnnotationException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Value.class)) {
                Value valueannotation = field.getAnnotation(Value.class);
                String referenceToValue = valueannotation.referenceToValue();
                log.info("annotationHandlerValueField внедряемое значение: {} ; referenceToValue - {}",
                        valueannotation.value(), referenceToValue);
                if (referenceToValue.equals("no reference")) {
                    try {
                        field.set(object, transformationValue(field, valueannotation.value()));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        log.debug("Ошибка в annotationHandlerValueField : ", e);
                    }
                } else {
                    assignmentOfValuesFields(field, object, splittingFileIntoValues(loadFileTxt()), referenceToValue);
                }
            } else {
                throw new NoValueAnnotationException();
            }
        }
    }

    /**
     * метод анализа метода объекта
     * 
     * @param object
     */
    private void annotationHandlerValueMethod(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            if (method.isAnnotationPresent(Value.class)) {
                Parameter[] parameters = method.getParameters();
                for (Parameter parameter : parameters) {
                    Value valueannotation = method.getAnnotation(Value.class);
                    log.info("annotationHandlerValueMethod внедряемое значение: {}", valueannotation.value());
                    try {
                        method.invoke(object, transformationValue(parameter, valueannotation.value()));
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        log.debug("Ошибка в annotationHandlerValueMethod : ", e);
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
    private StringBuffer loadFileTxt() {
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
    private Map<String, String> splittingFileIntoValues(StringBuffer stringBuf) {
        Map<String, String> treeMapVailues = new TreeMap<String, String>();
        String[] splitString = stringBuf.toString().split("(\\{)|(\\}\\W+)|(\\})");
        for (int i = 1; i < splitString.length; i++) {
            String[] masString1 = splitString[i].split("=");
            treeMapVailues.put(masString1[0], masString1[1]);
            masString1 = null;
        }
        log.info("Получаем список значений:");
        for (Map.Entry<String, String> entry : treeMapVailues.entrySet()) {
            log.info("Ключ -> {} Значение -> {};", entry.getKey(), entry.getValue());
        }
        return treeMapVailues;
    }

    /**
     * метод непостредственного внедрения значений в объект через ссылку (ключ) из
     * файла
     * 
     * @param field            - поле внедрения
     * @param object           - объект для внедрения
     * @param treeMapVailues   - для ссылки к значениям
     * @param referenceToValue - (ключ) ссылка
     */
    private void assignmentOfValuesFields(Field field, Object object, Map<String, String> treeMapVailues,
            String referenceToValue) {
        for (Map.Entry<String, String> entry : treeMapVailues.entrySet()) {
            if (referenceToValue.equals(entry.getKey())) {
                try {
                    field.set(object, transformationValue(field, entry.getValue()));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.debug("Ошибка в assignmentOfValuesFields : {}", e);
                }
            }
        }
    }

    /**
     * Метод преоброзования в тип в зависимости какой принемает поле анализируемого
     * объекта
     * 
     * @param field
     * @param value
     * @return
     */
    private Object transformationValue(Field field, String value) {
        Class<?> fieldType = field.getType();
        log.info("field: {}{}{}", fieldType.getSimpleName(), "; ", field.getType());
        if (fieldType.getSimpleName().equals("int")) {
            return Integer.parseInt(value);
        } else if (fieldType.getSimpleName().equals("String")) {
            return value;
        } else if (fieldType.getSimpleName().equals("Boolean")) {
            return Boolean.valueOf(value);
        }
        return Double.parseDouble(value);
    }

    /**
     * Метод преоброзования в тип в зависимости какой принемает метод анализируемого
     * объекта
     * 
     * @param parameter
     * @param value
     * @return
     */
    private Object transformationValue(Parameter parameter, String value) {
        Class<?> fieldType = parameter.getType();
        log.info("parameter: {}{}{}", fieldType.getSimpleName(), "; ", parameter.getType());
        if (fieldType.getSimpleName().equals("int")) {
            return Integer.parseInt(value);
        } else if (fieldType.getSimpleName().equals("String")) {
            return value;
        } else if (fieldType.getSimpleName().equals("Boolean")) {
            return Boolean.valueOf(value);
        }
        return Double.parseDouble(value);
    }
}
