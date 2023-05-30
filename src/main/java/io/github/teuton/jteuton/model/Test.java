package io.github.teuton.jteuton.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	private String id;
	private String repo;
	private String name;
	private List<String> authors = new ArrayList<>();
	private LocalDate date;
	private String description;
	private List<String> tags = new ArrayList<>();
	private List<File> files = new ArrayList<>();
	private File testDir;
	private File configFile;
	private File startFile;
	private Map<String, String> metadata = new HashMap<>();

	public Test(String id, String repo) {
		this.id = id;
		this.repo = repo;
	}

	public Test(File testFolder) throws FileNotFoundException {
		if (!testFolder.exists() || !testFolder.isDirectory()) {
			throw new FileNotFoundException("test " + testFolder.getName() + "not found!");
		}
		this.testDir = testFolder;
	}

	public Test() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRepo() {
		return repo;
	}

	public void setRepo(String repo) {
		this.repo = repo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public File getTestDir() {
		return testDir;
	}

	public void setTestDir(File testDir) {
		this.testDir = testDir;
	}

	public void setConfigFile(File configFile) {
		this.configFile = configFile;
	}

	public void setStartFile(File startFile) {
		this.startFile = startFile;
	}

	public File getConfigFile() {
		if (configFile == null) {
			configFile = new File(testDir, "config.yaml");
		}
		return configFile;
	}

	public File getStartFile() {
		if (startFile == null) {
			startFile = new File(testDir, "start.rb");
		}
		return startFile;
	}

	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", repo=" + repo + ", name=" + name + ", authors=" + authors + ", date=" + date
				+ ", description=" + description + ", tags=" + tags + ", files=" + files + ", testDir=" + testDir
				+ ", configFile=" + configFile + ", startFile=" + startFile + ", metadata=" + metadata + "]";
	}

}
