package io.github.teuton;

import java.io.StringWriter;

import org.jruby.embed.LocalContextScope;
import org.jruby.embed.PathType;
import org.jruby.embed.ScriptingContainer;

public class Teuton {

	public static String execute(String ... args) {
		return ruby("rubygems/bin/teuton", args);
	}

	@SuppressWarnings("unchecked")
	public static String ruby(String rubyfile, String ... args) {
		StringWriter writer = new StringWriter();
		ScriptingContainer container = new ScriptingContainer(LocalContextScope.SINGLETHREAD);
		container.getEnvironment().put("GEM_PATH", "classpath:/rubygems");
		container.setArgv(args);
		container.setOutput(writer);
		container.runScriptlet(PathType.CLASSPATH, rubyfile);
		return writer.toString();
	}
	
}
