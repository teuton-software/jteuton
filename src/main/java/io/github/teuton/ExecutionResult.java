package io.github.teuton;

import java.io.InputStream;

public class ExecutionResult {
	
	private String command;
	private Thread thread;
	private InputStream output;
	private InputStream error;
	
	public ExecutionResult(String command, Thread thread, InputStream output, InputStream error) {
		super();
		this.command = command;
		this.thread = thread;
		this.output = output;
		this.error = error;
	}

	public String getCommand() {
		return command;
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public InputStream getOutput() {
		return output;
	}
	
	public InputStream getError() {
		return error;
	}
	
}