package potemkin.i.yu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CacheElementTest {

	private CacheElement<String> cachEl;

	@BeforeEach
	public void setCachEl() {
		this.cachEl = new CacheElement<String>(1, "T");
	}

	@Test
	public void ComparableToStringReturnTrue() {
		CacheElement<String> cachElement = new CacheElement<String>(1, "T");
		assertEquals(cachElement.toString(), cachEl.toString());
	}
}
