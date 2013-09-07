/**
 * 
 */
package org.yroffin.rocksmith.core;

import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.rocksmith.builder.RocksmithSongBuilder;

/**
 * @author Yannick
 * 
 */
public class RocksmithSongBuilderTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {
		RocksmithSongBuilder builder = new RocksmithSongBuilder();
		builder.load(
				"src/test/resources/songs/ZZ Top - Sharp Dressed Man.gp3",
				new URL(
						"file:///"
								+ new File(
										"src/test/resources/songs/ZZ Top - Sharp Dressed Man.tg")
										.getAbsolutePath()));
		builder.writeFromDGuitar("target/ZZ Top - Sharp Dressed Man - DGuitar.xml");
		builder.writeFromTuxguitar("target/ZZ Top - Sharp Dressed Man - TuxGuitar.xml");
		fail("Not yet implemented");
	}
}
