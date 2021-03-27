package potemkin.i.yu;

/**
 * Параметрорезированный Класс CacheElement
 * 
 * @author Илья Пот
 * @param <T>
 */
public class CacheElement<T> {
	private T cacheElement;
	private int index;

	/**
	 * Конструктор с параметром index и объектом вложения
	 * 
	 * @param index        - индекс объекта
	 * @param cacheElement - объект вложения
	 */
	public CacheElement(int index, Object cacheElement) {
		this.cacheElement = (T) cacheElement;
		this.index = index;
	}

	@Override
	public String toString() {
		return "CacheElement [Element=" + cacheElement + ", index=" + index + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cacheElement == null) ? 0 : cacheElement.hashCode());
		result = prime * result + index;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CacheElement other = (CacheElement) obj;
		if (cacheElement == null) {
			if (other.cacheElement != null)
				return false;
		} else if (!cacheElement.equals(other.cacheElement))
			return false;
		if (index != other.index)
			return false;
		return true;
	}
}
