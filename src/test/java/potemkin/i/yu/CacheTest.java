package potemkin.i.yu;

import static org.junit.Assert.assertFalse;
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

	@Test
	public void countAdd() {
		int standard = 2;
		Cache<Integer> cache = new Cache<Integer>(10);
		cache.add(123, 1);
		cache.add(124, 2);
		int result = cache.getNextItem();
		assertEquals(standard, result);
	}
	
	@Test
	public void deleteElementTestReturnFalse() {
		Cache<Integer> cache = new Cache<Integer>(5);
		for(int i = 0; i < cache.getCapacity(); i++) {
			cache.add(i, i);
		}
		assertEquals(cache.getCapacity(), cache.getNextItem());
		cache.delete(1);
		boolean t = cache.getCapacity() == (cache.getNextItem());
		assertFalse(t);
	}
}
