package io.github.teuton;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeutonTest {
	
	@Test
	public void testVersion() {
		assertEquals("2.2.0", Teuton.version());
	}

}