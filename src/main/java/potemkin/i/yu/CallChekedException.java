package potemkin.i.yu;

import java.lang.reflect.Field;

/**
 * Класс запроса поля из объекта типа Cache, тестовый класс для вызова
 * проверяемого исключения
 * 
 * @author Илья Пот
 */
public class CallChekedException {
	private Cache cache;

	/**
	 * Конструктор класса ChekedException
	 * 
	 * @param cache
	 */
	public CallChekedException(Cache cache) {
		this.cache = cache;
	}

	/**
	 * Метод запроса поля nextItem
	 * 
	 * @return String - возврат имени поля (переменной)
	 * @throws NoSuchFieldException - выбрасываемое исключение
	 */
	public String fieldCache() throws NoSuchFieldException {
		Class example = cache.getClass();
		Field field = example.getField("nextItem");
		return "Тип переменной nextItem:" + field.getName();
	}
}
