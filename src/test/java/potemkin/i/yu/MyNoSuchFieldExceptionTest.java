package potemkin.i.yu;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MyNoSuchFieldExceptionTest {
	@Test
	public void ArrayWithException() throws MyNoSuchFieldException {
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
	public void ArrayWithExceptionAndMessageAndE() throws MyNoSuchFieldException {
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
	public void ArrayWithExceptionAndMessage() throws MyNoSuchFieldException {
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
	public void ArrayWithExceptionAndE() throws MyNoSuchFieldException {
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
