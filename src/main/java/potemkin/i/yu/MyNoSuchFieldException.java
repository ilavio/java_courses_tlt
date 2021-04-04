package potemkin.i.yu;

public class MyNoSuchFieldException extends Exception {

	public MyNoSuchFieldException() {
		super("Поле ненайдено (не существует)!");
	}

	public MyNoSuchFieldException(String s) {
		super(s);
	}

	public MyNoSuchFieldException(String message, Throwable cause) {
		super(message, cause);
	}

	public MyNoSuchFieldException(Throwable cause) {
		super("Поле ненайдено (не существует)!", cause);
	}

}
