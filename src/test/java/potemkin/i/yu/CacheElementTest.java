package potemkin.i.yu;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;

public class CacheElementTest {
	public CacheElement<String> cachEl;
	public CacheElement<String> cachEl2 = mock(CacheElement.class);

	@BeforeEach
	public void setCachEl() {
		this.cachEl = new CacheElement<String>(1, "T");
	}

	@Test
	public void ComparableToStringReturnTrue() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		assertEquals(cachElement.toString(), cachEl.toString());
	}

	@Test
	public void ComparableToStringReturnFalse() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		when(cachEl2.getCacheElement()).thenReturn("V");
		assertFalse(cachElement.getCacheElement().equals(cachEl2.getCacheElement()));
	}
	
	@Test
	public void checkedEqlesReturnTrue() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		assertEquals(cachElement, cachEl);
	}
	
	@Test
	public void checkedIndexReturnTrue() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		assertTrue(cachEl.getIndex() == cachElement.getIndex());
	}
}
