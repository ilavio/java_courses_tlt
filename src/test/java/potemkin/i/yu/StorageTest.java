package potemkin.i.yu;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StorageTest {
	@Test
	public void testSerchNextItem() {
		int expected = 3;
		Storage <String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		int result = storage.serchNextItem();
		assertEquals(expected, result);
	}
	
	@Test
	public void getLastTest() {
		String expected = "3";
		Storage <String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		String result = storage.getLast();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetElement() {
		String expected = "2";
		Storage <String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		String result = storage.get(1);
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetElementTrowException() throws MyIndexOutOfBoundException {
		Exception exception = assertThrows(MyIndexOutOfBoundException.class, () -> {
		String expected = "2";
		Storage <String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		String result = storage.get(12);
		assertEquals(expected, result);
		});
	}
}
