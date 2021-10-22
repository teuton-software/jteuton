package io.github.teuton;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.jruby.embed.LocalContextScope;
import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

public class Teuton {
	
	private static final String TEUTON_PATH = "rubygems/bin/teuton"; 
	private static final Pattern VERSION_PATTERN = Pattern.compile(".*\\(version *(.*)\\).*");

	@SuppressWarnings("unchecked")
	private static Writer ruby(Writer outputWriter, Writer errorWriter, String rubyfile, File currentDirectory, String ... args) {
		ScriptingContainer container = new ScriptingContainer(LocalContextScope.SINGLETHREAD);
		if (currentDirectory != null) {
			container.setCurrentDirectory(currentDirectory.getAbsolutePath());
		}
		container.getEnvironment().put("GEM_PATH", "uri:classloader:/rubygems");
		container.setArgv(args);
		container.setOutput(outputWriter);
		container.setError(errorWriter);
		container.runScriptlet(PathType.CLASSPATH, rubyfile);
		return outputWriter;
	}
	
	private static Writer ruby(Writer outputWriter, String rubyfile, File currentDirectory, String ... args) {
		return ruby(outputWriter, outputWriter, rubyfile, currentDirectory, args);
	}

	private static TeutonExecution ruby(String rubyfile, File currentDirectory, String ... args) throws IOException {
				
		PipedInputStream outputInputStream = new PipedInputStream();		
		Writer outputWriter = new OutputStreamWriter(new PipedOutputStream(outputInputStream));

		PipedInputStream errorInputStream = new PipedInputStream();		
		Writer errorWriter = new OutputStreamWriter(new PipedOutputStream(errorInputStream));

		Thread thread = new Thread(() -> ruby(outputWriter, errorWriter, TEUTON_PATH, currentDirectory, args));
		
		TeutonExecution result = new TeutonExecution();
		result.setCommand(currentDirectory.getAbsolutePath() + "$ " + rubyfile + " " + StringUtils.join(args, " "));
		result.setOutput(outputInputStream);
		result.setError(errorInputStream);
		result.setThread(thread);
		
		thread.start();
		
		return result;
	}

	private static String ruby(String rubyFile, String ... args) {
		return ruby(new StringWriter(), rubyFile, null, args).toString();
	}

	private static String execute(File currentDirectory, String ... args) {
		return ruby(new StringWriter(), TEUTON_PATH, currentDirectory, args).toString();
	}

	public static String execute(String ... args) {
		return ruby(TEUTON_PATH, args);
	}	

	public static String version() {
		String output = execute("version");
		Matcher m = VERSION_PATTERN.matcher(output.trim());
		if (m.matches()) {
			return m.group(1);
		}
		return null;
	}
	
	public static String readme(File challengeDirectory) {
		return execute(challengeDirectory, "readme", ".");
	}
	
	public static TeutonExecution play(File challengeDirectory, File configFile, File workingDirectory, List<String> casesId) throws IOException {
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
		return ruby(TEUTON_PATH, workingDirectory, args.toArray(new String[args.size()]));		
	}
	
}
