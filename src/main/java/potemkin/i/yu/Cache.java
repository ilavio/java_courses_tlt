/*
 * Все права защищены.
 * Регулируется лицензией Epam.
 * 
 */
package potemkin.i.yu;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * Параметрорезированный Класс Cache максимальная емкость закрепляется при
 * создании экземпляра класса через конструктор. Время выполнения операций
 * линейное. Массив используется для хранения элементов списка, он всекда не
 * меньше размера списка. При заполненнии емкости списка, при дольнейшей вставке
 * происходит удаление первого элемента списка и вставка в последнию ячейку.
 * Емкость списка не может быть увеличина в размере. Данный класс не
 * синхронизирован.
 * 
 * 
 * @author Илья Пот
 * @param <T> тип элементов в этом списке
 * @since 1
 */
@Slf4j
public class Cache<T> {
	/**
	 * Массив в котором хронятся элементы класса Cache
	 */
	private CacheElement<T>[] cache;

	/**
	 * Размер массива. Ипользуется для создания размера массива cache.
	 */
	private int capacity;

	/**
	 * nextItem - индекс для следующей вставки элемента
	 */
	private int nextItem;

	/**
	 * Конструктор
	 * 
	 * @param capacity - число для создания размера массива
	 */
	public Cache(int capacity) {
		this.capacity = capacity;
		this.cache = new CacheElement[this.capacity];
		nextItem = 0;
	}

	/**
	 * Метод добавления елемента и индекса элемента
	 * 
	 * @param element - елемент добавления
	 * @param index   - индекс элемента
	 */
	public void add(T element, int index) {
		if (nextItem > (cache.length - 1)) {
			deleteIndex(0);
			add(element, index);
		} else {
			cache[nextItem] = new CacheElement<>(index, element);
			nextItem += 1;
		}
	}

	/**
	 * Метод внутренний для удаления по индексу и сдвиг влево
	 * 
	 * @param index - индекс положения
	 */
	private void deleteIndex(int index) {
		if (index < cache.length) {
			cache[index] = null;
			for (int x = index; x < (nextItem - 1); x++) {
				cache[x] = cache[x + 1];
			}
			nextItem -= 1;
		}
	}

	/**
	 * Метод удаления по элементу и с двиг в лево
	 * 
	 * @param element - объект который надо удалить
	 */
	public void delete(T element) {
		int nenaideno = 1;
		for (int i = 0; i < cache.length; i++) {
			if (cache[i].getCacheElement().equals(element)) {
				cache[i] = null;
				nenaideno = 0;
				for (int a = i; a < (nextItem - 1); a++) {
					cache[a] = cache[a + 1];
					cache[a + 1] = null;
				}
				nextItem -= 1;
			}
			if (nenaideno == 0) {
				break;
			}
		}
		if (nenaideno == 1) {
			log.debug("Элемент не найден при удалении");
		}
	}

	/**
	 * Метод поиска наличия объекта
	 * 
	 * @param element - искомый объект
	 * @return boolean - нашел true, не нашел false
	 */
	public boolean isPresent(T element) {
		for (int i = 0; i < nextItem; i++) {
			if (cache[i].getCacheElement().equals(element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Метод возврата объекта по элементу
	 * 
	 * @param element - объект который надо вернуть
	 * @return возвращаемый объект, если объект не найден вернет null
	 */
	public T get(T element) {
		CacheElement<T> elementX = null;
		if (element != null) {
			for (int i = 0; i < nextItem; i++) {
				if (cache[i].getCacheElement().equals(element)) {
					elementX = cache[i];
					deleteIndex(i);
					log.info("methot get: {}: {}", elementX.cacheElement, elementX.getIndex());
					cache[nextItem] = elementX;
					break;
				}
			}
		}
		return elementX.getCacheElement();
	}

	/**
	 * Метод возврата объекта по индексу
	 * 
	 * @param index - индекс объекта для возврата
	 * @return T - возврат объекта, если объект не найден вернет null
	 */
	public T get(int index) {
		StringBuffer strBuf = new StringBuffer();
		T elementX = null;
		CacheElement<T> cachEl = null;
		for (int i = 0; i < nextItem; i++) {
			if (cache[i].getIndex() == index) {
				elementX = cache[i].getCacheElement();
				cachEl = cache[i];
				deleteIndex(i);
				cache[nextItem] = cachEl;
				break;
			}
		}
		return elementX;
	}

	/**
	 * Метод очистки Cashe и массива cashe
	 */
	public void clear() {
		for (int i = 0; i < cache.length; i++) {
			cache[i] = null;
		}
		cache = null;
		capacity = 0;
		nextItem = 0;
		log.info("Cache [cashe= {} next: {}]", Arrays.toString(cache), nextItem);
	}

	public int getCapacity() {
		return capacity;
	}

	public int getNextItem() {
		return nextItem;
	}

	@Override
	public String toString() {
		return new StringBuffer().append("Cache [cashe=").append(Arrays.toString(cache)).append(" next: ")
				.append(nextItem).append("]").toString();
	}
}
