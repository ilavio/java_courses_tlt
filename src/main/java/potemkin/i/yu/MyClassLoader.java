package potemkin.i.yu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyClassLoader extends ClassLoader {
	public String pathClass;
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File file = new File(pathClass);
		Class newClassMy = null;
		if(!file.isFile()) {
			throw new ClassNotFoundException("Класс не найден(не существует)!");
		}
		try(BufferedInputStream buf2 = new BufferedInputStream(new FileInputStream(file))){
			byte[] bytemas = new byte[(int) file.length()];
			buf2.read(bytemas);
			return  defineClass(name, bytemas, 0, bytemas.length);
		} catch (FileNotFoundException e) {
			log.info("Ошибка в методе findClass 1");
			e.printStackTrace();
			return super.findClass(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newClassMy;
	}
	
}
