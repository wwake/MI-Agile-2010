import static org.junit.Assert.assertEquals;

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
	public void testToStringShouldReturnTheEnclosedStringData() {
		
		String result = "just a string";
		TextLine line = new TextLine(result);
		assertEquals(result, line.toString());
	}
}
/* I'd probably do these tests slightly differently, around empty string and non-empty string. '
@Test emptyString
assertEquals(0, emptyLine.width());
assertEquals("", emptyLine.toString());

@Test nonEmptyString
String text = "just a string"
assertEquals(text.length(), line.width())
assertEquals(text, line.toString())

[with initialization for the two lines]
*/