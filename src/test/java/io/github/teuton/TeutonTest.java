package io.github.teuton;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import io.github.teuton.jteuton.Teuton;

public class TeutonTest {
	
	private static final File CHALLENGE = new File("src/test/resources/challenge");
	private static final String VERSION = ResourceBundle.getBundle("jteuton").getString("teuton.version");

	@Test
	public void testVersion() {
		assertEquals(VERSION, Teuton.version());
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
		assertThat(Teuton.readme(CHALLENGE), containsString("# challenge"));
	}

	@Test
	public void testCheckSuccess() throws Exception {
		assertThat(Teuton.check(CHALLENGE), containsString("GROUP: test"));
	}
	
	@Test(expected = Exception.class)
	public void testCheckFail() throws Exception {
		File notExist = new File("src/test/resources/not-exist");
		Teuton.check(notExist);
	}
	
	@Test
	public void testPanelConfig() {
		assertThat(Teuton.getPanelConfig(CHALLENGE), containsString(":global:"));
	}

	
}