package potemkin.i.yu;

/**
 * Класс обработки исключения checked MyNoSuchFieldException
 * 
 * @author Илья Пот
 */
public class MyNoSuchFieldException extends Exception {
	
	/**
	 * Дефолтный конструктор класса
	 */
	public MyNoSuchFieldException() {
		super("Поле ненайдено (не существует)!");
	}

	/**
	 * Конструктор класса с атрибутом MyNoSuchFieldException
	 * 
	 * @param s - оставляемое сообщение
	 */
	public MyNoSuchFieldException(String s) {
		super(s);
	}

	/**
	 * Конструктор класса с атрибутами MyNoSuchFieldException
	 * 
	 * @param message - оставляемое сообщение
	 * @param cause   - развернутое представлние причин исключения
	 */
	public MyNoSuchFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Конструктор класса с атрибутом MyNoSuchFieldException
	 * 
	 * @param cause - развернутое представлние причин исключения
	 */
	public MyNoSuchFieldException(Throwable cause) {
		super("Поле ненайдено (не существует)!", cause);
	}

}
