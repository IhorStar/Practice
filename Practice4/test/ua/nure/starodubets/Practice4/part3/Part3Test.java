package ua.nure.starodubets.Practice4.part3;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Part3Test {

	private static final String ENCODING = "Cp1251";

	private static final String REGEXP_FOR_INTEGER = "(^|\\s)(\\d+)(\\s|$)";
	private static final String REGEXP_FOR_DOUBLE = "(^|\\s)([\\d+]*\\.\\d+)(\\s|$)";
	private static final String REGEXP_FOR_CHAR = "(?i)(^|(?<=\\s))[a-zà-ÿ]($|(?=\\s))";
	private static final String REGEXP_FOR_STRING = "[À-ßà-ÿa-zA-Z]{2,}";

	Part3 part3;

	@Before
	public void constructorTest() {
		part3 = new Part3("part3.txt");
	}

	@Test
	public void testGetString() {
		part3.getString();
		part3.setFileName("incorrect filename");
		part3.getString();
	}

	@Test
	public void testGetIntegerValue() {
		int counter = 0;
		String[] arr = part3.getIntegerValue().split(" ");
		Pattern pattern = Pattern.compile(REGEXP_FOR_INTEGER);
		Matcher matcher = pattern.matcher(part3.getString());
		while (matcher.find()) {
			++counter;
		}
		Assert.assertEquals(counter, arr.length);
	}

	@Test
	public void testGetDoubleValue() {
		int counter = 0;
		String[] arr = part3.getDoubleValue().split(" ");
		Pattern pattern = Pattern.compile(REGEXP_FOR_DOUBLE);
		Matcher matcher = pattern.matcher(part3.getString());
		while (matcher.find()) {
			++counter;
		}
		Assert.assertEquals(counter, arr.length);
	}

	@Test
	public void testGetCharValue() {
		int counter = 0;
		String[] arr = part3.getCharValue().split(" ");
		Pattern pattern = Pattern.compile(REGEXP_FOR_CHAR);
		Matcher matcher = pattern.matcher(part3.getString());
		while (matcher.find()) {
			++counter;
		}
		Assert.assertEquals(counter, arr.length);
	}

	@Test
	public void testGetStringValue() {
		int counter = 0;
		String[] arr = part3.getStringValue().split(" ");
		Pattern pattern = Pattern.compile(REGEXP_FOR_STRING);
		Matcher matcher = pattern.matcher(part3.getString());
		while (matcher.find()) {
			++counter;
		}
		Assert.assertEquals(counter, arr.length);
	}



	@Test
	public void testInput() throws UnsupportedEncodingException {
		System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble\nstop".getBytes(ENCODING)));
		part3.input();
		System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble\nfdsfds".getBytes(ENCODING)));
		part3.input();
		System.setIn(System.in);
	}

	@Test
	public void testMain() throws UnsupportedEncodingException {
		System.setIn(new ByteArrayInputStream("char\nString\nint\ndouble\nstop".getBytes(ENCODING)));
		Part3.main(new String[] {});
		System.setIn(System.in);
	}
}
