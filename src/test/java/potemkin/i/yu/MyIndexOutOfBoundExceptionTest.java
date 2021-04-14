package potemkin.i.yu;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MyIndexOutOfBoundExceptionTest {
	@Test
	public void callMyIndexOutOfBoundException() throws MyIndexOutOfBoundException {
		int[] integer = new int[] { 1, 2, 3 };
		try {
			for (int i = 0; i < integer.length + 1; i++) {
				int x = integer[i];
			}
		} catch (Exception e) {
			Exception exception = assertThrows(MyIndexOutOfBoundException.class, () -> {
				throw new MyIndexOutOfBoundException();
			});
		}
	}

	@Test
	public void notCallMyIndexOutOfBoundException() {
		int[] integer = new int[] { 1, 2, 3 };
		try {
			for (int i = 0; i < integer.length; i++) {
				int x = integer[i];
			}
		} catch (Exception e) {
			Exception exception = assertThrows(MyIndexOutOfBoundException.class, () -> {
				throw new MyIndexOutOfBoundException();
			});
		}
	}

	@Test
	public void callMyIndexOutOfBoundExceptionMassege() {
		Exception exception = assertThrows(MyIndexOutOfBoundException.class, () -> {
			throw new MyIndexOutOfBoundException("AAA");
		});
	}

	@Test
	public void callMyIndexOutOfBoundExceptionAndE() throws MyIndexOutOfBoundException {
		int[] integer = new int[] { 1, 2, 3 };
		try {
			for (int i = 0; i < integer.length + 1; i++) {
				int x = integer[i];
			}
		} catch (Exception e) {
			Exception exception = assertThrows(MyIndexOutOfBoundException.class, () -> {
				throw new MyIndexOutOfBoundException("FFFF", e);
			});
		}
	}
}
