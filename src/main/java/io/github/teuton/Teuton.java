package io.github.teuton;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class Teuton extends Thread {
	
	private static final String TEUTON_PATH = "rubygems/bin/teuton"; 
	private static final Pattern VERSION_PATTERN = Pattern.compile(".*\\(version *(.*)\\).*");

	private static String execute(File currentDirectory, String ... args) {
		return Ruby.run(TEUTON_PATH, new StringWriter(), currentDirectory, args).toString();
	}

	public static String execute(String ... args) {
		return Ruby.run(TEUTON_PATH, args);
	}	

	public static String version() {
		String output = execute("version");
		Matcher m = VERSION_PATTERN.matcher(output.trim());
		return m.matches() ? m.group(1) : null;
	}
	
	public static String readme(File challengeDirectory) {
		return execute(challengeDirectory, "readme", ".");
	}
	
	public static Thread play(File challengeDirectory, File configFile, File outputDirectory, List<String> casesId, Consumer<String> output, Consumer<String> error) throws IOException {
		List<String> args = new ArrayList<>();
		args.add("play");
		args.add("--no-color");
		if (configFile != null) {
			args.add("--cpath=" + configFile.getAbsolutePath());
		}
		if (casesId != null && !casesId.isEmpty()) {
			args.add("--case=" + StringUtils.join(casesId, ","));
		}
		args.add("--export=json");
		args.add(challengeDirectory.getAbsolutePath());
		return Ruby.run(TEUTON_PATH, output, error, outputDirectory, args.toArray(new String[args.size()]));		
	}
	
}
