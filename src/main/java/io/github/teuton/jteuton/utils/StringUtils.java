package io.github.teuton.jteuton.utils;

public class StringUtils {
	
	public static String clearColorCodes(String input) {
		return input.replaceAll("\u001B\\[[;\\d]*m", "");
	}
	
	public static String dosToUnix(String input) {
		return input.replaceAll("\r", "");
	}

}
