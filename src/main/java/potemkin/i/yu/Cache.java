package potemkin.i.yu;

import java.util.Arrays;

/**
 * Параметрорезированный Класс Cashe
 * 
 * @author Илья Пот
 */
public class Cache<T> {
	private Object[] cashe;
	private int capacity;
	private int nextItem = 0;

	/**
	 * Конструктор
	 * 
	 * @param capacity - число для создания размера массива
	 */
	public Cache(int capacity) {
		this.capacity = capacity;
		this.cashe = new Object[capacity];
	}

	/**
	 * Метод для вычесления следующей позиции для вставки в массиве
	 * 
	 * @return int - номер последней пустой ячейки
	 */
	private int serchNextItem() {
		if (cashe != null) {
			for (int i = cashe.length - 1; i >= 0; i -= 1) {
				if (i == 0) {
					nextItem = i;
				}
				if (cashe[i] != null) {
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
	 * Метод добавления
	 * 
	 * @param element - объект который добавляем
	 */
	public void add(T element) {
		if (serchNextItem() > (cashe.length - 1)) {
			deleteIndex(0);
			add(element);
		} else {
			cashe[serchNextItem()] = element;
		}
	}

	public void add(Object element, int index) {
		if (serchNextItem() > (cashe.length - 1)) {
			deleteIndex(0);
			add(element, index);
		} else {
			cashe[serchNextItem()] = new CacheElement<T>(index, (T) element);
		}
	}

	/**
	 * Метод внутренний для удаления по индексу и сдвиг влево
	 * 
	 * @param index - индекс положения
	 */
	private void deleteIndex(int index) {
		if (index < cashe.length) {
			cashe[index] = null;
			for (int x = index; x < (serchNextItem() - 1); x++) {
				cashe[x] = cashe[x + 1];
			}
			cashe[serchNextItem() - 1] = null;
		}
	}

	/**
	 * Метод удаления по объекту и с двиг в лево
	 * 
	 * @param element - объект который надо удалить
	 */
	public void delete(T element) {
		int nenaideno = 1;
		for (int i = 0; i < cashe.length; i++) {
			if (element.equals(cashe[i])) {
				cashe[i] = null;
				nenaideno = 0;
				for (int a = i; a < (serchNextItem() - 1); a++) {
					cashe[a] = cashe[a + 1];
					cashe[a + 1] = null;
				}
			}
		}
		if (nenaideno == 1) {
			System.out.println("Элемент не найден при удалении");
		}
	}

	/**
	 * Метод поиска наличия объекта
	 * 
	 * @param element - искомый объект
	 * @return boolean - нашел true, не нашел false
	 */
	public boolean isPresent(T element) {
		boolean ret = false;
		for (int i = 0; (element != null) && (i < cashe.length); i++) {
			if (element.equals(cashe[i])) {
				ret = true;
				return ret;
			}
		}
		return ret;
	}

	/**
	 * Метод возврата объекта по объекту
	 * 
	 * @param element - объект который надо вернуть
	 * @return возвращаемый объект, если объект не найден вернет null
	 */
	public T get(T element) {
		T elementX = null;
		if (element != null) {
			for (int i = 0; i < serchNextItem(); i++) {
				if (cashe[i].equals(element)) {
					elementX = (T) cashe[i];
					deleteIndex(i);
					cashe[serchNextItem()] = elementX;
					break;
				}
			}
		}
		return elementX;
	}

	/**
	 * Метод возврата объекта по индексу
	 * 
	 * @param index - индекс объекта для возврата
	 * @return T - возврат объекта, если объект не найден вернет null
	 */
	public T get(int index) {
		T elementX = null;
		for (int i = 0; i < serchNextItem(); i++) {
			if (cashe.length > i) {
				elementX = (T) cashe[i];
				deleteIndex(i);
				cashe[serchNextItem()] = elementX;
				break;
			}
		}
		return elementX;
	}

	/**
	 * Метод очистки Cashe и массива cashe
	 */
	public void clear() {
		for (int i = 0; i < cashe.length; i++) {
			cashe[i] = null;
		}
		cashe = null;
		capacity = 0;
		nextItem = 0;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public String toString() {
		return "Cache [cashe=" + Arrays.toString(cashe) + " next: " + serchNextItem() + "]";
	}
}
