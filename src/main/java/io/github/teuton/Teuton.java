package io.github.teuton;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import io.github.teuton.ruby.Ruby;

public class Teuton {
	
	private static final String BIN_PATH = "rubygems/bin/teuton"; 
	private static final Pattern VERSION_PATTERN = Pattern.compile("teuton version (.*).*");

	private static String execute(File workingDirectory, String ... args) {
		return Ruby.run(BIN_PATH, new StringWriter(), workingDirectory, args).toString();
	}

	public static String execute(String ... args) {
		return Ruby.run(BIN_PATH, args);
	}	

	public static ExecutionResult run(File workingDirectory, File configFile, File outputDirectory, List<String> casesId, Consumer<String> output, Consumer<String> error) throws IOException {
		List<String> args = new ArrayList<>();
		args.add("run");
		args.add("--no-color");
		if (configFile != null) {
			args.add("--cpath=" + configFile.getAbsolutePath());
		}
		if (casesId != null && !casesId.isEmpty()) {
			args.add("--case=" + StringUtils.join(casesId, ","));
		}
		args.add("--export=json");
		args.add(workingDirectory.getAbsolutePath());
		return Ruby.run(BIN_PATH, output, error, outputDirectory, args.toArray(new String[0]));		
	}
	
	public static ExecutionResult play(File workingDirectory, File configFile, File outputDirectory, List<String> casesId, Consumer<String> output, Consumer<String> error) throws IOException {
		return run(workingDirectory, configFile, outputDirectory, casesId, output, error);
	}
	
	public static String version() {
		String output = execute("version");
		Matcher m = VERSION_PATTERN.matcher(output.trim());
		return m.matches() ? m.group(1) : null;
	}
	
	public static String readme(File workingDirectory) {
		return execute(workingDirectory, "readme", ".");
	}
	
	public static String help(String command) {
		return execute("help", command);
	}
	
	public static String help() {
		return execute("help");
	}
	
	public static String create(File directory) {
		return execute("new", directory.getAbsolutePath());
	}
	
	public static String check(File directory, boolean panelConfig) throws Exception {
		Writer errorWriter = new StringWriter();
		String output = Ruby.run(
				BIN_PATH,						// teuton bin path 
				new StringWriter(), 			// output
				errorWriter, 					// error
				null, 							// working directory
				"check", 						// teuton command
				directory.getAbsolutePath(), 	// directory (check command argument)
				panelConfig ? "--panelconfig" : "--no-panelconfig"	// panel config or not panel config
		).toString();		
		String error = errorWriter.toString();
		if (error.isEmpty() && output.contains("[ERROR]")) {
			error = output;
		}
		if (!error.isEmpty()) {
			throw new Exception(error);
		}
		return output;
	}
	
	public static String check(File directory) throws Exception {
		return check(directory, false);
	}
	
	public static String getPanelConfig(File directory) throws Exception {
		return check(directory, true);
	}
	
}
