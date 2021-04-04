package potemkin.i.yu;

import java.lang.reflect.Field;

public class ChekedException {
	private Cache cache;
	public ChekedException(Cache cache) {
		this.cache = cache;
	}
	
	public String fieldCache() throws NoSuchFieldException {
		Class example = cache.getClass();
		Field field = example.getField("nextItem");
		return "Тип переменной nextItem:" + field.getName();
	}
}
