package io.github.teuton;

import static io.github.teuton.utils.StringUtils.dosToUnix;
import static io.github.teuton.utils.StringUtils.removeColorCodes;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.github.teuton.ruby.Ruby;
import io.github.teuton.utils.IniUtils;

public class TeutonGet {

	private static final ObjectMapper YAML_MAPPER = new ObjectMapper(new YAMLFactory());
	private static final String BIN_PATH = "rubygems/bin/teutonget";
	private static final Pattern VERSION_PATTERN = Pattern.compile("teutonget \\(version *(.*)\\).*");
	private static final Pattern SEARCH_PATTERN = Pattern.compile("^\\(x(\\d+)\\) (.*):(.*)$");
	private static final File INI_FILE = new File(System.getProperty("user.home"), ".config/teuton/repos.ini");

	public static String execute(File workingDirectory, String... args) {
		return Ruby.run(BIN_PATH, new StringWriter(), workingDirectory, args).toString();
	}

	public static String execute(String... args) {
		return Ruby.run(BIN_PATH, args);
	}

	public static String version() {
		String output = execute("version");
		Matcher m = VERSION_PATTERN.matcher(output.trim());
		return m.matches() ? m.group(1) : null;
	}

	public static void init() {
		execute("init");
	}

	public static void refresh() {
		execute("refresh");
	}

	public static List<Test> search(String repoName, String filter) {
		filter = (!StringUtils.isBlank(repoName) ? repoName + ":" : "") + filter;
		String output = execute("search", filter);
		output = removeColorCodes(output);
		output = dosToUnix(output);
		return Arrays.asList(output.split("\n"))
			.stream()
			.map(line -> {
				Matcher m = SEARCH_PATTERN.matcher(line);
				if (m.matches()) {
//					int matches = Integer.parseInt(m.group(1)); 
					String name = m.group(2);
					String testId = m.group(3);
					return new Test(testId, name);
				}
				return null;
			})
			.filter(test -> test != null)
			.collect(Collectors.toList());
	}

	public static List<Test> search(String filter) {
		return search(null, filter);
	}
	
	public static List<Test> getAllTests() {
		return search("ALL", "ALL");
	}

	@SuppressWarnings("unchecked")
	public static Test getInfo(String repoName, String testId) throws Exception {
		
		String output = execute("info", repoName + ":" + testId);
		output = removeColorCodes(output);
		
		Map<String, String> info = YAML_MAPPER.readValue(output, Map.class);
		
		Test test = new Test(testId, repoName);
		test.setName(info.remove("name"));
		test.setAuthors(
			Arrays.asList(info.remove("author").split(","))
				.stream()
				.map(String::trim)
				.collect(Collectors.toList())
		);
		test.setDescription(info.remove("desc"));
		test.setDate(LocalDate.parse(info.remove("date"), DateTimeFormatter.ISO_DATE));
		test.setTags(
			Arrays.asList(info.remove("tags").split(","))
				.stream()
				.map(String::trim)
				.collect(Collectors.toList())
		);
		test.setFiles(
			Arrays.asList(info.remove("files").split(","))
				.stream()
				.map(String::trim)
				.map(File::new)
				.collect(Collectors.toList())
		);
		test.setMetadata(info);
		
		return test;
	}

	public static List<Repo> getRepos() throws IOException {
		List<Repo> repos = new ArrayList<>();
		if (INI_FILE.exists()) {
			Map<String, Map<String, String>> ini = IniUtils.parseIniFile(INI_FILE);
			ini.entrySet().stream()
				.map(entry -> new Repo(entry.getKey(), entry.getValue()))
				.forEach(repos::add);
		}
		return repos;
	}
	
	public static Repo getRepo(String name) throws IOException {
		return getRepos()
				.stream()
				.filter(repo -> repo.getName().equals(name))
				.findFirst()
				.orElse(null);
	}
	
	public static void main(String[] args) throws Exception {
		Test test = getInfo("teuton.es", "sistemas.3/scripting/usermin");
		System.out.println(test);
	}

}
