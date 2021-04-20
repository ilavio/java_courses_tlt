package com.potemkin.i.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import com.potemkin.i.Human;
import com.potemkin.i.Student;

public class ClassAnalyzer {
    
    public static void start(Object object) {
        if(initializationEntity(object)) {
            annotationHandlerValueField((Human) object);
        }
    }
    
    public static boolean initializationEntity(Object object) {
        return object.getClass().isAnnotationPresent(Entity.class);
    }

    public static void annotationHandlerValueField(Object human) {
        Class<?> clazz = human.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Value valueannotation = field.getAnnotation(Value.class);
            if (!valueannotation.equals(null)) {
                field.setAccessible(true);
                String valueStr = (String) valueannotation.name();
                int valueInt = (int) valueannotation.age();
                System.out.println("Fields value: " + valueStr + ", " + valueInt + "; there is an annotate: "
                        + field.isAnnotationPresent(Value.class));
                if (!valueStr.equals("") && field.getName().equals("name")) {
                    try {
                        field.set(human, valueStr);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                if (valueInt != 0 && field.getName().equals("age")) {
                    try {
                        field.setInt(human, valueInt);
                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void annotationHandlerValueField(Student student) {
        Class<?> clazz = student.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Value.class)) {
                Value valueannotation = field.getAnnotation(Value.class);
                if (!field.getAnnotation(Value.class).equals(null)) {
                    String valueStr = valueannotation.name();
                    int valueInt = valueannotation.age();
                    System.out.println("Fields value: " + valueStr + ", " + valueInt + "; there is an annotate: "
                            + field.isAnnotationPresent(Value.class));
                    if (!valueStr.equals("") && field.getName().equals("name")) {
                        try {
                            field.set(student, valueStr);
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (valueInt != 0 && field.getName().equals("age")) {
                        try {
                            field.setInt(student, valueInt);
                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void annotationHandlerValueHumanParametr(Human human) {
        Class<?> clazz = human.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                if (parameter.isAnnotationPresent(Value.class)) {
                    Value valueannotation = parameter.getAnnotation(Value.class);
                    // if (!valueannotation.equals(null) ) {
                    String valueStr = (String) valueannotation.name();
                    int valueInt = (int) valueannotation.age();
                    System.out.println("Parametrs value: " + valueStr + ", " + valueInt + " ;");
                    if (!valueStr.equals("no name") && parameter.getName().equals("arg0")) {
                        try {
                            method.invoke(human, valueStr);
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (valueInt != 0) {
                        try {
                            method.invoke(human, (Integer) valueInt);
                        } catch (IllegalArgumentException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    // }
                }
            }
        }
    }

    public static void annotationHandlerValueStudentParametr(Student student) {
        Class<?> clazz = student.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            method.setAccessible(true);
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter.isAnnotationPresent(Value.class));
                if (parameter.isAnnotationPresent(Value.class)) {
                    Value valueannotation = parameter.getAnnotation(Value.class);
                    if (!valueannotation.equals(null)) {
                        String valueStr = (String) valueannotation.name();
                        int valueInt = (int) valueannotation.age();
                        System.out.println("Parametrs value: " + valueStr + ", " + valueInt + " ;");
                        if (!valueStr.equals("no name") && parameter.getName().equals("arg0")) {
                            try {
                                method.invoke(student, valueStr);
                            } catch (IllegalArgumentException | IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        if (valueInt != 0) {
                            try {
                                method.invoke(student, (Integer) valueInt);
                            } catch (IllegalArgumentException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

//    public static Class<?>[] loadClasses() {
//        File file = new File(PATH);
//        File[] fileMasClass = selectingFiles(file);
//        Class<?>[] classMassiv = new Class<?>[fileMasClass.length];
//        for (int i = 0; i < fileMasClass.length; i++) {
//            Class<?> clazz = fileMasClass[i].getClass();
//            classMassiv[i] = clazz;
//        }
//        return classMassiv;
//    }
//
//    public static File[] selectingFiles(File file) {
//        FileFilter filter = new FileFilter() {
//            public boolean accept(File file) {
//                return file.getName().endsWith("java");
//            }
//        };
//        return file.listFiles(filter);
//    }
//
//    public String setName(File file) {
//        int pointIndex = file.getName().lastIndexOf(".");
//        String name = file.getName().substring(0, pointIndex);
//        return name;
//    }
}
