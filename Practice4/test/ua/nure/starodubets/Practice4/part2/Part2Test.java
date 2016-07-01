package ua.nure.starodubets.Practice4.part2;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Part2Test {

	private String inputFileName = "part2.txt";
	private String outputFileName = "part2_sorted.txt";

	Part2 part2;

	@Before
	public void ConstructorTest() {
		part2 = new Part2(inputFileName, outputFileName, "CP1251");
	}

	@Test
	public void testSortNumbers() {
		Assert.assertEquals("3 9 16 16 18 21 23 23 29 30 ", part2.sortNumbers("30 23 16 16 9 23 3 18 21 29"));
	}

	@Test
	public void testFillRandomNumbers() {
		Assert.assertNotEquals(part2.fillRandomNumbers(),
				part2.fillRandomNumbers());
	}

	@Test
	public void testCalculateRandomInt() {
		Assert.assertEquals(new Integer(0).getClass(),
				new Integer(part2.calculateRandomInt()).getClass());
	}

	@Test
	public void testWriteFirstFile() {
		File file = new File("part2.txt");
		file.setReadOnly();
		part2.writeInputFile();
		file.setWritable(true);
		part2.writeInputFile();
		part2.setEncoding("incorrect encoding");
		part2.writeInputFile();
	}

	@Test
	public void testWriteSecondFile() {
		File file = new File(inputFileName);
		File file_sorted = new File(outputFileName);
		file.setReadOnly();
		file_sorted.setReadOnly();
		part2.writeOutputFile();
		file.setWritable(true);
		file_sorted.setWritable(true);
		part2.writeOutputFile();
		part2.setEncoding("incorrect encoding");
		part2.writeOutputFile();
	}

	@Test
	public void testOutput() {
		part2.output();
		part2.setInputFileName("incorrect filename");
		part2.output();
	}

	@Test
	public void testMain() {
		Part2.main(new String[] {});
	}
}
