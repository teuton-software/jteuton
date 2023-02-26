package io.github.teuton.utils;

public class StringUtils {
	
	public static String removeColorCodes(String input) {
		return input.replaceAll("\u001B\\[[;\\d]*m", "");
	}
	
	public static String dosToUnix(String input) {
		return input.replaceAll("\r", "");
	}

}
