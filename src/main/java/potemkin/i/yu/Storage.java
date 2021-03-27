package potemkin.i.yu;

import java.util.Arrays;

public class Storage<T> {
	private Object[] storage;
	private Cache<T> innerCashe;
	private int capacity;

	/**
	 * Дефолтный конструктор с внутренним размером массивов
	 */
	public Storage() {
		capacity = 10;
		this.storage = new Object[capacity];
		this.innerCashe = new Cache<T>(capacity);
	}

	/**
	 * Конструктор добавления массива объектов
	 * 
	 * @param storage - массив объектов
	 */
	public Storage(T[] storage) {
		capacity = 10;
		if (capacity < storage.length) {
			this.storage = new Object[expand(storage.length)];
			this.innerCashe = new Cache<T>(10);
		} else {
			this.storage = new Object[10];
			this.innerCashe = new Cache<T>(10);
		}
		System.arraycopy(storage, 0, this.storage, 0, storage.length);
	}

	/**
	 * Метод рассчета увелечения кол-ва ячеек
	 * 
	 * @param length - данное кол-во ячеек
	 * @return следующее кол-во ячеек
	 */
	private int expand(int length) {
		while (length >= capacity) {
			capacity = (int) ((double) capacity * 1.5);
		}
		return capacity;
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
			storage = new Object[expand(storageCopy.length)];
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
	private int serchNextItem() {
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
			if (innerCashe.isPresent(elementCopy)) {
				innerCashe.delete(elementCopy);
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
		innerCashe.clear();
	}

	/**
	 * Метод возврата последнего элемента
	 * 
	 * @return возвращает элемент массива
	 */
	public T getLast() {
		return (T) storage[serchNextItem() - 1];
	}

	/**
	 * Метод возврата из массива innerCashe, если нет, то из массива storage
	 * 
	 * @param index - индекс позиции елемента которого надо вернуть
	 * @return возвращает элемент по индексу, если индекс привышает пределы то
	 *         возвращает null
	 */
	public T get(int index) {
		T elementCopy = null;
		if (index >= 0 & index < innerCashe.getCapacity()) {
			elementCopy = (T) innerCashe.get(index);
			if (elementCopy != null) {
				return elementCopy;
			}
		} else if (index >= 0 & index < storage.length) {
			elementCopy = (T) storage[index];
			innerCashe.add(elementCopy);
			return elementCopy;
		}
		return elementCopy;
	}

	@Override
	public String toString() {
		return "Storage [storage=" + Arrays.toString(storage) + " next: " + serchNextItem() + "\ninnerCashe="
				+ innerCashe + "]";
	}

}
