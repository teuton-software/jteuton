package io.github.teuton;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TeutonTest {
	
	private File challenge = new File("src/test/resources/challenge");

	
	@Test
	public void testVersion() {
		assertEquals("2.4.5", Teuton.version());
	}
	
	@Test
	public void testHelp() {
		assertThat(Teuton.help(), containsString("teuton [run] [OPTIONS] DIRECTORY"));
	}
	
	@Test
	public void testCreate() throws Exception {
		File challenge = new File(System.getProperty("java.io.tmpdir"), "challenge");
		File configFile = new File(challenge, "config.yaml");
		File startFile = new File(challenge, "start.rb");
		Teuton.create(challenge);
		assertTrue(challenge.exists());
		assertTrue(configFile.exists());
		assertTrue(startFile.exists());
		Thread.sleep(2000L); // waits 2secs till config.yaml file is unlocked by teuton
		FileUtils.deleteDirectory(challenge);
	}
	
	@Test
	public void testReadme() {
		assertThat(Teuton.readme(challenge), containsString("# challenge"));
	}

	@Test
	public void testCheckSuccess() throws Exception {
		assertThat(Teuton.check(challenge), containsString("GROUP: test"));
	}
	
	@Test(expected = Exception.class)
	public void testCheckFail() throws Exception {
		File notExist = new File("src/test/resources/not-exist");
		Teuton.check(notExist);
	}
	
	@Test
	public void testPanelConfig() {
		assertThat(Teuton.getPanelConfig(challenge), containsString(":global:"));
	}

	
}