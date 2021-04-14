package potemkin.i.yu;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Параметрорезированный Класс Storage
 * 
 * @author Илья Пот
 */
@Slf4j
public class Storage<T> {
	private Object[] storage;
	private Cache<T> cache;
	private int capacity;

	/**
	 * Дефолтный конструктор с внутренним размером массивов
	 */
	public Storage() {
		capacity = 10;
		this.storage = new Object[capacity];
		this.cache = new Cache<T>(capacity);
	}

	/**
	 * Конструктор добавления массива объектов
	 * 
	 * @param storage - массив объектов
	 */
	public Storage(T[] storage) {
		capacity = 10;
		if (capacity < storage.length) {
			capacity = (int) ((double) capacity * 1.5);
			this.storage = new Object[capacity];
			this.cache = new Cache<T>(10);
		} else {
			this.storage = new Object[10];
			this.cache = new Cache<T>(10);
		}
		System.arraycopy(storage, 0, this.storage, 0, storage.length);
	}

	/**
	 * Метод добавления елемента
	 * 
	 * @param element - елемент добавления
	 */
	public void add(T element) {
		if (serchNextItem() == storage.length) {
			Object[] storageCopy = new Object[storage.length];
			System.arraycopy(storage, 0, storageCopy, 0, storage.length);
			storage = null;
			capacity = (int) ((double) capacity * 1.5);
			storage = new Object[capacity];
			System.arraycopy(storageCopy, 0, storage, 0, storageCopy.length);
			storageCopy = null;
		}
		storage[serchNextItem()] = element;
	}

	/**
	 * Метод внутренний поиска следующей позиции для вставки
	 * 
	 * @return возвращает следующую позицию для вставки
	 */
	public int serchNextItem() {
		int nextItem = 0;
		if (storage != null) {
			for (int i = storage.length - 1; i >= 0; i -= 1) {
				if (i == 0) {
					nextItem = i;
				}
				if (storage[i] != null) {
					nextItem = i + 1;
					break;
				}
			}
		} else {
			nextItem = 0;
		}
		return nextItem;
	}

	/**
	 * Удаляем element содержащиеся в storege и cashe
	 */
	public void delete() {
		T elementCopy;
		for (int i = 0; i < serchNextItem(); i++) {
			elementCopy = (T) storage[i];
			if (cache.isPresent(elementCopy)) {
				cache.delete(elementCopy);
				storage[i] = null;
				for (int a = i; a < (serchNextItem() - 1); a++) {
					storage[a] = storage[a + 1];
					storage[a + 1] = null;
				}
				break;
			}
		}
		elementCopy = null;
	}

	/**
	 * Метод очистки Storage, очистка массива storege и cashe
	 */
	public void clear() {
		for (int i = 0; i < storage.length; i++) {
			storage[i] = null;
		}
		storage = null;
		capacity = 0;
		cache.clear();
	}

	/**
	 * Метод возврата последнего элемента
	 * 
	 * @return возвращает элемент массива
	 */
	public T getLast() {
		log.info("getLast: {}", storage[serchNextItem() - 1]);
		return (T) storage[serchNextItem() - 1];
	}

	/**
	 * Метод возврата из массива innerCashe, если нет, то из массива storage
	 * 
	 * @param index - индекс позиции елемента которого надо вернуть
	 * @return возвращает элемент по индексу, если индекс привышает пределы то
	 *         возвращает null
	 * @throws MyIndexOutOfBoundException - если выходим за пределы массива
	 */
	public T get(int index) throws MyIndexOutOfBoundException {
		T elementCopy = null;
		if (index < 0 || index > storage.length) {
			throw new MyIndexOutOfBoundException();
		}
		if (cache.get(index) != null) {
			return cache.get(index);
		} else if (index >= 0 & index < storage.length) {
			elementCopy = (T) storage[index];
			cache.add((T) elementCopy, index);
			log.info("Storage element: {}", elementCopy);
			return elementCopy;
		}
		return elementCopy;
	}

	@Override
	public String toString() {
		return "Storage [storage=" + Arrays.toString(storage) + " next: " + serchNextItem() + "\ninnerCashe=" + cache
				+ "]";
	}

	public Object[] getStorage() {
		return storage;
	}
}
