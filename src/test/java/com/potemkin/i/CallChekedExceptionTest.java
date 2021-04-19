package com.potemkin.i;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.potemkin.i.exception.MyNoSuchFieldException;

public class CallChekedExceptionTest {
    Cache<Integer> cache;

    @BeforeEach
    public void createCache() {
        this.cache = new Cache<Integer>(5);
    }

    @Test
    public void fieldCache() {
        CallChekedException callChekedException = new CallChekedException(cache);
        try {
            callChekedException.fieldCache();
        } catch (MyNoSuchFieldException e) {
            Exception exception = assertThrows(MyNoSuchFieldException.class, () -> {
                callChekedException.fieldCache();
            });
        }
    }

    @Test
    public void fieldCacheNotNull() {
        assertNotNull(cache.getNextItem());
    }

    @Test
    public void callChekedExceptionTestNotNull() {
        CallChekedException callChekedException = new CallChekedException(cache);
        assertNotNull(callChekedException);
    }
}
