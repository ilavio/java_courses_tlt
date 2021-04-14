package potemkin.i.yu;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	public static void main(String[]args) {
		String path = "C:\\myClasses";
		File sorce = new File(path);
		MyClassLoader loaderMyClass = new MyClassLoader();
		if(sorce.isDirectory()) {
			File [] file = sorce.listFiles();
			for(int i = 0; i < file.length; i++) {
				try {
					String [] pathClass = file[i].getAbsolutePath().split(".class");
					System.out.println(pathClass[0]);
					System.out.println(file[i].getName());
					Class example = loaderMyClass.loadClass(pathClass[0]);
					System.out.println(example);
				} catch (ClassNotFoundException e) {
					System.err.println("Ошибка");
					e.printStackTrace();
				}
			}
		}
		stackOverError();
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
