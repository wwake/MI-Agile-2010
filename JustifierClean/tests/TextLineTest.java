import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TextLineTest {

	@Test
	public void widthIsTheLengthOfTheEnclosedString() {
		TextLine line = new TextLine("");
		assertEquals(0, line.width());
		
		line = new TextLine("this is a string");
		assertEquals(16, line.width());
	}

	@Test
	public void toStringShouldReturnTheEnclosedStringData() {
		String result = "just a string";
		TextLine line = new TextLine(result);
		assertEquals(result, line.toString());
	}
}
