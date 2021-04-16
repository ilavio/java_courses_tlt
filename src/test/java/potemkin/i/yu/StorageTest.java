package potemkin.i.yu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StorageTest {
	Storage<String> storageC = new Storage<String>();

	@BeforeEach
	public void createStorage() {
		storageC.add("A");
		storageC.add("B");
	}

	@Test
	public void testSerchNextItem() {
		int expected = 3;
		Storage<String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		int result = storage.serchNextItem();
		assertEquals(expected, result);
	}

	@Test
	public void getLastTest() {
		String expected = "3";
		Storage<String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		String result = storage.getLast();
		assertEquals(expected, result);
	}

	@Test
	public void testGetElement() {
		String expected = "2";
		Storage<String> storage = new Storage<String>();
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
			Storage<String> storage = new Storage<String>();
			storage.add("1");
			storage.add("2");
			storage.add("3");
			String result = storage.get(12);
			assertEquals(expected, result);
		});
	}

	@Test
	public void testRunDelete() {
		Storage<String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		int a = storage.serchNextItem();
		storage.delete();
		storage.delete();
		int b = storage.serchNextItem();
		assertTrue(a == b);
	}

	@Test
	public void clearReturnNull() {
		Storage<String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		assertNotNull(storage.getStorage());
		storage.clear();
		assertNull(storage.getStorage());
	}

	@Test
	public void compareToString() {
		Storage<String> storageA = new Storage<String>();
		storageA.add("1");
		storageA.add("2");
		storageA.add("3");
		Storage<String> storageB = new Storage<String>();
		storageB.add("1");
		storageB.add("2");
		storageB.add("3");
		assertEquals(storageA.toString(), storageB.toString());
	}

	@Test
	public void getLastTestReturnFalse() {
		String expected = "4";
		Storage<String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		String result = storage.getLast();
		assertFalse(expected == result);
	}

	@Test
	public void testGetElementReturnNull() {
		Storage<String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		assertNull(storage.get(9));
	}

	@Test
	public void testGetElementReturnFalse() {
		String expected = "5";
		Storage<String> storage = new Storage<String>();
		storage.add("1");
		storage.add("2");
		storage.add("3");
		String result = storage.get(1);
		assertFalse(expected.equals(result));
	}

	@Test
	public void compareStorages() {
		Storage<String> storage = new Storage<String>();
		storage.add("A");
		storage.add("B");
		assertEquals(storage.toString(), storageC.toString());
	}

	@Test
	public void limitCheckConstructor() {
		String[] str = new String[] { "A", "B", "C", "D", "E", "F", "G", "I", "R", "T", "S" };
		Storage<String> storage = new Storage<String>(str);
		assertNotNull(storage.getLast());
	}

	@Test
	public void limitCheck() {
		Storage<String> storage = new Storage<String>();
		for (int i = 0; i < 12; i++) {
			storage.add(Integer.toString(i));
		}
		assertNotNull(storage.getLast());
	}
}
