import static org.junit.Assert.*;

import org.junit.Test;


public class TextLineTest {

	@Test
	public void testWidthIsTheLengthOfTheEnclosedString() {
		TextLine line = new TextLine("");
		assertEquals(0, line.width());
		
		line = new TextLine("this is a string");
		assertEquals(16, line.width());
	}

	@Test
	public void testIsLineIsAlwaysTrue() {
		assertTrue(new TextLine("").isLine());
	}

	@Test
	public void testToStringShouldReturnTheEnclosedStringData() {
		
		String result = "just a string";
		TextLine line = new TextLine(result);
		assertEquals(result, line.toString());
	}

}
