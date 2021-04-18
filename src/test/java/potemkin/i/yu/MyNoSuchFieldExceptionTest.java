package potemkin.i.yu;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import potemkin.i.yu.exception.MyNoSuchFieldException;

public class MyNoSuchFieldExceptionTest {
	@Test
	public void arrayWithException() throws MyNoSuchFieldException {
		String[] mass = new String[] { "G", "S", "S" };
		for (int i = 0; i < 4; i++) {
			if (i > mass.length) {
				Exception exception = assertThrows(MyNoSuchFieldException.class, () -> {
					throw new MyNoSuchFieldException();
				});
			}
		}
	}

	@Test
	public void arrayWithExceptionAndMessageAndE() throws MyNoSuchFieldException {
		String[] mass = new String[] { "G", "S", "S" };
		final String message = "GSS";
		for (int i = 0; i < 4; i++) {
			try {
				String s = mass[i];
			} catch (Exception e) {
				Exception exception = assertThrows(MyNoSuchFieldException.class, () -> {
					throw new MyNoSuchFieldException(message, e);
				});
			}
		}
	}

	@Test
	public void notMyNoSuchFieldExceptionTest() {
		String[] mass = new String[] { "G", "S", "S" };
		final String message = "GSS";
		for (int i = 0; i < 3; i++) {
			try {
				String s = mass[i];
				assertEquals(s, mass[i]);
			} catch (Exception e) {
				Exception exception = assertThrows(MyNoSuchFieldException.class, () -> {
					throw new MyNoSuchFieldException(message, e);
				});
			}
		}
	}

	@Test
	public void arrayWithExceptionAndMessage() throws MyNoSuchFieldException {
		String[] mass = new String[] { "G", "S", "S" };
		for (int i = 0; i < 4; i++) {
			if (i > mass.length) {
				Exception exception = assertThrows(MyNoSuchFieldException.class, () -> {
					throw new MyNoSuchFieldException("GGGG");
				});
			}
		}
	}

	@Test
	public void arrayWithExceptionAndE() throws MyNoSuchFieldException {
		try {
			String[] mass = new String[] { "G", "S", "S" };
			for (int i = 0; i < 4; i++) {
				String s = mass[i];
			}
		} catch (Exception e) {
			Exception exception = assertThrows(MyNoSuchFieldException.class, () -> {
				throw new MyNoSuchFieldException(e);
			});
		}
	}
}
