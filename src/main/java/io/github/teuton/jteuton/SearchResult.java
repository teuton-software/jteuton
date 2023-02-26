package io.github.teuton.jteuton;

public class SearchResult {

	private int matches;
	private String repoName;
	private String testId;

	public SearchResult(int matches, String repoName, String testId) {
		super();
		this.matches = matches;
		this.repoName = repoName;
		this.testId = testId;
	}

	public int getMatches() {
		return matches;
	}

	public void setMatches(int matches) {
		this.matches = matches;
	}

	public String getRepoName() {
		return repoName;
	}

	public void setRepoName(String repoName) {
		this.repoName = repoName;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	@Override
	public String toString() {
		return "SearchResult [matches=" + matches + ", repoName=" + repoName + ", testId=" + testId + "]";
	}

}
