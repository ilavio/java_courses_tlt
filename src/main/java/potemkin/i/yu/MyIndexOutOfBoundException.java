package potemkin.i.yu;

/**
 * Класс исключения unchecked MyIndexOutOfBoundException в случае выхода за
 * пределы массива
 * 
 * @author Илья Пот
 */
public class MyIndexOutOfBoundException extends RuntimeException {

	/**
	 * Дефолтный конструктор класса
	 */
	public MyIndexOutOfBoundException() {
		super("Вы вышли за пределы массива!");
	}

	/**
	 * Конструктор класса с атрибутами MyIndexOutOfBoundException
	 * 
	 * @param message - оставляемое сообщение
	 * @param cause   - развернутое представлние причин исключения
	 */
	public MyIndexOutOfBoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Конструктор класса с атрибутом MyIndexOutOfBoundException
	 * 
	 * @param message - оставляемое сообщение
	 */
	public MyIndexOutOfBoundException(String message) {
		super(message);
	}
}
