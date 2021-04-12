package potemkin.i.yu;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CallChekedExceptionTest {
	Cache<Integer> cache;

	@BeforeEach
	public void createCache() {
		this.cache = new Cache<Integer>(5);
	}

	@Test
	public void fieldCache() throws NoSuchFieldException {
		CallChekedException callChekedException = new CallChekedException(cache);
		Exception exception = assertThrows(NoSuchFieldException.class, () -> {
			callChekedException.fieldCache();
		});
	}
	
	@Test
	public void fieldCacheNotNull() {
		assertNotNull(cache.getNextItem());
	}
}
