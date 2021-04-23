package com.potemkin.i;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * MainTest
 * 
 * @author Илья Пот
 */
public class MainTest {
    @Test
    public void testMain() {
        Main myMain = new Main();
        Main.main(null);
        assertNotNull(myMain);
    }
}
