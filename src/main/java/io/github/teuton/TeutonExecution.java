package io.github.teuton;

import java.io.InputStream;

public class TeutonExecution {

	private String command;
	private Thread thread;
	private InputStream output;
	private InputStream error;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public InputStream getOutput() {
		return output;
	}

	public void setOutput(InputStream output) {
		this.output = output;
	}

	public InputStream getError() {
		return error;
	}

	public void setError(InputStream error) {
		this.error = error;
	}

}
