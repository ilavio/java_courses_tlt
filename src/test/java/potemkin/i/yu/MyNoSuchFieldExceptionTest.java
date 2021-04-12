package potemkin.i.yu;

import static org.junit.Assert.assertThrows;

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
	public void ArrayWithExceptionAndMessage() throws MyNoSuchFieldException {
		String[] mass = new String[] { "G", "S", "S" };
		final String message = "GSS";
		for (int i = 0; i < 4; i++) {
			try {
				String s = mass[i];
			}catch(Exception e) {
					Exception exception = assertThrows(MyNoSuchFieldException.class, () -> {
						throw new MyNoSuchFieldException(message, e);
					});
			}
		}
	}
}
