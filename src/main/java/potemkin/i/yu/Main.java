package potemkin.i.yu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
	public static void main(String[]args) {
		String path = "C:\\myClasses";
		File sorce = new File(path);
		MyClassLoader loaderMyClass = new MyClassLoader();
		if(sorce.isDirectory()) {
			File [] file = sorce.listFiles();
			for(int i = 0; i < file.length; i++) {
				try {
					int pointIndex = file[i].getName().lastIndexOf(".");
			        String name = file[i].getName().substring(0, pointIndex);
			        String pathClass = file[i].getAbsolutePath();
					log.info(name);
					log.info(pathClass);
					loaderMyClass.pathClass = pathClass;
					Class example = loaderMyClass.loadClass(name);
					Object obj = example.newInstance();  
					System.out.println(obj); 
				} catch (ClassNotFoundException e) {
					log.info("Ошибка");
					e.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		stackOverError();
		memoryError();
	}
	
	public static void stackOverError() {
		String string;
		string = new String("Рекурсия");
		stackOverError();
	}
	
	public static void memoryError() {
		ArrayList<String> list = new ArrayList<String>();
		while(true) {
			list.add(new String("Бесконечность и далее"));
		}
	}
}
