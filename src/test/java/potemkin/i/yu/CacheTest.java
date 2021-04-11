package potemkin.i.yu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CacheTest {
	@Mock
	public CacheElement cacheElement;
	@InjectMocks
	public Cache cache;
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
	public void  countAdd() {
		int standard = 2;
		Cache<Integer> cache = new Cache<Integer>(10);
		cache.add(123, 1);
		cache.add(124, 2);
		int result = cache.getNextItem();
		assertEquals(standard, result);
	}
}
