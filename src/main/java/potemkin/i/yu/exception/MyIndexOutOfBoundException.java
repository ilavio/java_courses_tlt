/*
 * Все права защищены.
 * Регулируется лицензией Epam.
 * 
 */
package potemkin.i.yu.exception;

/**
 * Класс исключения unchecked MyIndexOutOfBoundException в случае выхода за
 * пределы массива, который может быть выброшен во время нормальной работы.
 * Непроверенные исключения не должны объявляться в методе или конструкторе.
 * Предложение может быть вызвано выполнением метода или конструктора и
 * распространяться за пределы метода или конструктора.
 * 
 * @author Илья Пот
 * @since 1.0
 */
public class MyIndexOutOfBoundException extends RuntimeException {

	/**
	 * Дефолтный конструктор класса Создает новое исключение с со стандартным
	 * сообщением.
	 */
	public MyIndexOutOfBoundException() {
		super("Вы вышли за пределы массива!");
	}

	/**
	 * Конструктор класса с атрибутами MyIndexOutOfBoundException с сообщеним и
	 * причиной
	 * 
	 * @param message - оставляемое сообщение
	 * @param cause   - развернутое представлние причин исключения
	 */
	public MyIndexOutOfBoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Конструктор класса с атрибутом MyIndexOutOfBoundException, с сообщением
	 * 
	 * @param message - оставляемое сообщение
	 */
	public MyIndexOutOfBoundException(String message) {
		super(message);
	}
}
