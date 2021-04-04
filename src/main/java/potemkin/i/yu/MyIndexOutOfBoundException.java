package potemkin.i.yu;

public class MyIndexOutOfBoundException extends RuntimeException {
	public MyIndexOutOfBoundException() {
		super("Вы вышли за пределы массива!");
	}
	public MyIndexOutOfBoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public MyIndexOutOfBoundException(String message) {
		super(message);
	}
}
