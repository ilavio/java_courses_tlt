package potemkin.i.yu;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheElementTest {
	private CacheElement<String> cachEl;
	private CacheElement<String> cachEl2 = mock(CacheElement.class);

	@BeforeEach
	public void setCachEl() {
		this.cachEl = new CacheElement<String>(1, "T");
	}

	@Test
	public void comparableToStringReturnTrue() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		assertEquals(cachElement.toString(), cachEl.toString());
	}

	@Test
	public void comparableToStringReturnFalse() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		when(cachEl2.getCacheElement()).thenReturn("V");
		assertNotEquals(cachElement.getCacheElement(), cachEl2.getCacheElement());
	}
	
	@Test
	public void checkedEqlesReturnTrue() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		assertEquals(cachElement, cachEl);
	}
	
	@Test
	public void checkedIndexReturnTrue() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		assertEquals(cachEl.getIndex(), cachElement.getIndex());
	}
	
	@Test
	public void checkedNotNull() {
		assertNotNull(cachEl);
	}
	
	@Test
	public void checkedElement() {
		assertNotNull(cachEl.getCacheElement());
	}
	
	@Test
	public void checkedHashCode() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		assertEquals(cachEl.hashCode(), cachElement.hashCode());
	}
	
	@Test
	public void checkedDefaultConstructorReturnNull() {
		CacheElement<String> cachElement = new CacheElement<String>();
		assertNull(cachElement.getCacheElement());
	}
}
