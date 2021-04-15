package potemkin.i.yu;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	private static final String PATH = "C:\\myClasses";

	public static void main(String[] args) {
		File source = new File(PATH);
		MyClassLoader loaderMyClass = new MyClassLoader();
		extractingFiles(source, loaderMyClass);
		stackOverError();
		memoryError();
	}

	public static void extractingFiles(File source, MyClassLoader loaderMyClass) {
		if (source.isDirectory()) {
			File[] file = source.listFiles();
			for (int i = 0; i < file.length; i++) {
				try {
					Class example = loaderMyClass.customloadClass(file[i]);
					Object obj = example.getDeclaredConstructor().newInstance();
					System.out.println(obj);
				} catch (ClassNotFoundException e) {
					log.debug("Ошибка ClassNotFoundException");
					e.printStackTrace();
				} catch (InstantiationException e) {
					log.debug("Ошибка InstantiationException");
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					log.debug("Ошибка IllegalAccessException");
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					log.debug("Ошибка IllegalArgumentException");
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					log.debug("Ошибка InvocationTargetException");
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					log.debug("Ошибка NoSuchMethodException");
					e.printStackTrace();
				} catch (SecurityException e) {
					log.debug("Ошибка SecurityException");
					e.printStackTrace();
				}
			}
		}
	}

	public static void stackOverError() {
		new String("Рекурсия");
		stackOverError();
	}

	public static void memoryError() {
		List<String> list = new ArrayList<String>();
		while (true) {
			list.add(new String("Бесконечность и далее"));
		}
	}
}
