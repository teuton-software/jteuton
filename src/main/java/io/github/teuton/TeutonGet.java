package io.github.teuton;

import java.io.File;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.github.teuton.ruby.Ruby;

public class TeutonGet {
	
	private static final String BIN_PATH = "rubygems/bin/teutonget"; 
	private static final Pattern VERSION_PATTERN = Pattern.compile("teutonget \\(version *(.*)\\).*");

	public static String execute(File workingDirectory, String ... args) {
		return Ruby.run(BIN_PATH, new StringWriter(), workingDirectory, args).toString();
	}

	public static String execute(String ... args) {
		return Ruby.run(BIN_PATH, args);
	}
	
	public static String version() {
		String output = execute("version");
		Matcher m = VERSION_PATTERN.matcher(output.trim());
		return m.matches() ? m.group(1) : null;
	}
	
}
