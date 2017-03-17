package net.kyivstar.leonchyk.service;

import net.kyivstar.leonchyk.entity.Webcam;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bohdan Leonchyk
 * @date 16.03.2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class WebcamServiceTest {

	private static boolean isEmpty = true;

	private static List<Webcam> webcamList = new ArrayList<>();

	@Autowired
	private WebcamService webcamService;

	@Before
	public void init() {
		if (isEmpty) {
			Webcam webcam = new Webcam();
			webcam.setIdentifier("webcam111");
			webcam.setLocation("Kyiv");

			webcamList.add(webcam);

			webcam = new Webcam();
			webcam.setIdentifier("webcam222");
			webcam.setLocation("Lviv");

			webcamList.add(webcam);

			webcam = new Webcam();
			webcam.setIdentifier("webcam333");
			webcam.setLocation("Dnipro");

			webcamList.add(webcam);

			isEmpty = false;
		}
	}

	@Test
	public void testNotNull() {
		Assert.assertNotNull("Expected not null", webcamService.findAllWebcams());
	}

	@Test
	@Sql(scripts = {"classpath:import-test.sql"})
	public void testNotEmpty() {
		Assert.assertNotEquals("Expected not empty", 0, webcamService.findAllWebcams().size());
	}

	@Test
	public void testFixedSize() {
		Assert.assertEquals("Wrong size", 3, webcamService.findAllWebcams().size());
	}

	@Test
	public void testGetOne() {
		Webcam result = webcamService.findByIdentifier(webcamList.get(0).getIdentifier());
		Assert.assertEquals("Incorrect identifier", webcamList.get(0), result);
	}

	@Test
	public void testAll() {
		Assert.assertThat("Webcam is missed", webcamList, CoreMatchers.hasItems(webcamList.get(0)));
		Assert.assertThat("Webcam is missed", webcamList, CoreMatchers.hasItems(webcamList.get(1)));
		Assert.assertThat("Webcam is missed", webcamList, CoreMatchers.hasItems(webcamList.get(2)));
	}
}
