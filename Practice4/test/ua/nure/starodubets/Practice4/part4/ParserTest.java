package ua.nure.starodubets.Practice4.part4;

import java.io.File;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	Parser parser;

	@Before
	public void testConstructor() {
		parser = new Parser(new File("part4.txt"));
	}

	@Test
	public void testInvalidConstructor() {
		parser = new Parser(new File("incorrect name"));
	}

	@Test
	public void iteratorTest() {
		Iterator<String> iterator = parser.iterator();
		while (iterator.hasNext()) {
			Assert.assertEquals(parser.getMatcher().group(), iterator.next());
		}
	}

	@Test(expected = UnsupportedOperationException.class)
	public void iteratorExceptionTest() {
		Iterator<String> iterator = parser.iterator();
		iterator.remove();
	}
}
