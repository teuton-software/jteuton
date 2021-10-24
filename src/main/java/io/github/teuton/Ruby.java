package io.github.teuton;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.function.Consumer;

import org.jruby.embed.LocalContextScope;
import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

import io.github.teuton.utils.StreamCharacterConsumer;

public class Ruby {
	
	private static final String GEM_PATH = "uri:classloader:/rubygems"; 
	
	@SuppressWarnings("unchecked")
	public static Writer run(String rubyFile, Writer output, Writer error, File currentDirectory, String ... args) {
		ScriptingContainer container = new ScriptingContainer(LocalContextScope.SINGLETHREAD);
		if (currentDirectory != null) {
			container.setCurrentDirectory(currentDirectory.getAbsolutePath());
		}
		container.getEnvironment().put("GEM_PATH", GEM_PATH);
		container.setArgv(args);
		container.setOutput(output);
		container.setError(error);
		container.runScriptlet(PathType.CLASSPATH, rubyFile);
		return output;
	}
	
	public static Writer run(String rubyFile, Writer outputWriter, File currentDirectory, String ... args) {
		return run(rubyFile, outputWriter, outputWriter, currentDirectory, args);
	}

	public static String run(String rubyFile, File currentDirectory, String ... args) {
		return run(rubyFile, new StringWriter(), currentDirectory, args).toString();
	}

	public static String run(String rubyFile, String ... args) {
		return run(rubyFile, null, args).toString();
	}

	public static Thread run(String rubyFile, Consumer<String> output, Consumer<String> error, File currentDirectory, String ... args) throws IOException {
		
		PipedInputStream outputInputStream = new PipedInputStream();		
		StreamCharacterConsumer outputConsumer = new StreamCharacterConsumer(outputInputStream, output);		
		Writer outputWriter = new OutputStreamWriter(new PipedOutputStream(outputInputStream));
		
		PipedInputStream errorInputStream = new PipedInputStream();		
		StreamCharacterConsumer errorConsumer = new StreamCharacterConsumer(errorInputStream, error);
		Writer errorWriter = new OutputStreamWriter(new PipedOutputStream(errorInputStream));
					
		Thread thread = new Thread(() -> {
			outputConsumer.start();
			errorConsumer.start();
			run(rubyFile, outputWriter, errorWriter, currentDirectory, args);
			outputConsumer.requestStop();
			errorConsumer.requestStop();
		});
		thread.setName(rubyFile);
		thread.start();
	
		return thread;
	}

}
