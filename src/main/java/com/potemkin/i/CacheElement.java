/*
 * Все права защищены.
 * Регулируется лицензией Epam.
 * 
 */
package com.potemkin.i;

/**
 * Параметрорезированный Класс CacheElement обертывает значение из Cache <T>
 * типа.
 * 
 * @author Илья Пот
 * @param <T>
 * @since 1.0
 */
public class CacheElement<T> {
    public T cacheElement;
    private int index;

    /**
     * Дефолтный конструктор Класс CacheElement типа T
     */
    public CacheElement() {
    }

    /**
     * Конструктор с параметром index и объектом вложения
     * 
     * @param index        - индекс объекта
     * @param cacheElement - объект вложения
     */
    public CacheElement(int index, T cacheElement) {
        this.cacheElement = cacheElement;
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

    public T getCacheElement() {
        return cacheElement;
    }

    public int getIndex() {
        return index;
    }
}
