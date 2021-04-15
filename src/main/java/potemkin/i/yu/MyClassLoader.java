package potemkin.i.yu;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

/**
 * Класс переапределенный загрузчик классов MyClassLoader
 * 
 * @author Илья Пот
 */
@Slf4j
public class MyClassLoader extends ClassLoader {
	private String pathClass;

	/**
	 * Метод загрузки класса
	 * 
	 * @param name - имя класса
	 * @return Class<?>
	 */
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		File file = new File(pathClass);
		Class newClassMy = null;
		if (!file.isFile()) {
			throw new ClassNotFoundException("Класс не найден(не существует)!");
		}
		try (BufferedInputStream buf2 = new BufferedInputStream(new FileInputStream(file))) {
			byte[] bytemas = new byte[(int) file.length()];
			buf2.read(bytemas);
			return defineClass(name, bytemas, 0, bytemas.length);
		} catch (FileNotFoundException e) {
			log.info("Ошибка в методе findClass 1");
			e.printStackTrace();
			return super.findClass(name);
		} catch (IOException e) {
			log.info("Ошибка в методе findClass 2");
			e.printStackTrace();
		}
		return newClassMy;
	}

	public void setPathClass(String pathClass) {
		this.pathClass = pathClass;
	}

	/**
	 * Метод извлечения имени класса без рассширения и абсолютного пути
	 * 
	 * @param file - класса
	 * @return - имя класса
	 */
	public String setNameSetPathClass(File file) {
		int pointIndex = file.getName().lastIndexOf(".");
		String name = file.getName().substring(0, pointIndex);
		setPathClass(file.getAbsolutePath());
		log.info(pathClass);
		log.info(name);
		return name;
	}

	/**
	 * Метод вызова loadClass
	 * 
	 * @param file - класса
	 * @return - Class
	 * @throws ClassNotFoundException
	 */
	public Class<?> customloadClass(File file) throws ClassNotFoundException {
		return loadClass(setNameSetPathClass(file));
	}
}
