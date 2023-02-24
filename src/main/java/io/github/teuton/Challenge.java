package io.github.teuton;

import java.io.File;

public class Challenge {

	private File challengeFolder;
	private File configFile;
	private File startFile;
	
	public Challenge(File challengeFolder) {
		this.challengeFolder = challengeFolder;
	}

	public File getChallengeFolder() {
		return challengeFolder;
	}

	public File getConfigFile() {
		return configFile;
	}

	public File getStartFile() {
		return startFile;
	}
	
	public void create() {
		// TODO
	}
	
	public void play(File outputDirectory) {
		
	}
	
	public String readme() {
		return "";
	}

}
