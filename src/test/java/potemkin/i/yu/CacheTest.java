package potemkin.i.yu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CacheTest {
	@Test
	public void checkIsPresentReturnTrue() {
		Cache<Integer> cache = new Cache<Integer>(10);
		cache.add(123, 1);
		boolean result = cache.isPresent(123);
		assertTrue(result);
	}
	@Test
	public void getElementFromCache() {
		Cache<Integer> cache = new Cache<Integer>(10);
		cache.add(123, 1);
		assertEquals(123, cache.get(1));
	}
}
