package io.github.teuton;

import static org.junit.Assert.assertEquals;

import java.util.ResourceBundle;

import org.junit.Test;

import io.github.teuton.jteuton.TeutonGet;

public class TeutonGetTest {
	
	private static final String VERSION = ResourceBundle.getBundle("jteuton").getString("teuton-get.version");
	
	@Test
	public void testVersion() {		
		assertEquals(VERSION, TeutonGet.version());
	}
		
}