package potemkin.i.yu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {

	@Override
	protected Class<?> findClass(String path) throws ClassNotFoundException {
		File file = new File(path + ".class");
		int pointIndex = file.getName().lastIndexOf(".");
        String name = file.getName().substring(0, pointIndex);
		Class newClassMy = null;
		if(!file.isFile()) {
			throw new ClassNotFoundException("Класс не найден(не существует)!");
		}
		try(BufferedInputStream buf2 = new BufferedInputStream(new FileInputStream(file))){
			byte[] bytemas = new byte[(int) file.length()];
			buf2.read(bytemas);
			return  defineClass(name, bytemas, 0, bytemas.length);
		} catch (FileNotFoundException e) {
			System.out.println("Ошибка в методе findClass 1");
			e.printStackTrace();
			return super.findClass(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newClassMy;
	}
	
}
