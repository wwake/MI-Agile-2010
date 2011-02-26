import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.util.Vector;

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

	@Test
	public void entriesShouldReturnCollectionContainingOnlyBlockEntryWithItselfAndNoOffset() {
		TextLine aLine = new TextLine("1");
		Vector<BlockEntry> theEntries = aLine.entries();
		assertEquals(1, theEntries.size());
		BlockEntry entry = theEntries.elementAt(0);
		assertSame(aLine, entry.line());
		assertEquals(0, entry.offset());
	}
}
