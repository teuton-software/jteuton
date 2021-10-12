package io.github.teuton;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TeutonTest {
	
	@Test
	public void testVersion() {
		assertEquals("teuton (version 2.1.11)\n", Teuton.execute("version"));
	}

}