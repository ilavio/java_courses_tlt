/*
 * Все права защищены.
 * Регулируется лицензией Epam.
 * 
 */
package potemkin.i.yu.exception;

/**
 * Класс исключения checked MyNoSuchFieldException, в случае отсутствии искомого
 * поля (переменной) класса. Проверенные исключения необходимо объявить в
 * предложении метода или конструктора, если они могут быть вызваны выполнением
 * метода или конструктора и распространяться за пределы метода или
 * конструктора.
 * 
 * @author Илья Пот
 */
public class MyNoSuchFieldException extends Exception {

	/**
	 * Дефолтный конструктор класса. Создает новое исключение с со стандартным
	 * сообщением.
	 */
	public MyNoSuchFieldException() {
		super("Поле ненайдено (не существует)!");
	}

	/**
	 * Конструктор класса с атрибутом MyNoSuchFieldException с сообщеним
	 * 
	 * @param s - оставляемое сообщение
	 */
	public MyNoSuchFieldException(String s) {
		super(s);
	}

	/**
	 * Конструктор класса с атрибутами MyNoSuchFieldException с сообщеним и
	 * причиной
	 * 
	 * @param message - оставляемое сообщение
	 * @param cause   - развернутое представлние причин исключения
	 */
	public MyNoSuchFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Конструктор класса с атрибутом MyNoSuchFieldException с причиной
	 * 
	 * @param cause - развернутое представлние причин исключения
	 */
	public MyNoSuchFieldException(Throwable cause) {
		super("Поле ненайдено (не существует)!", cause);
	}

}
