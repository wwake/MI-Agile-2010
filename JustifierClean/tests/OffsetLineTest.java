import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;


public class OffsetLineTest {

	@Test
	public void constructorSetsGivenLineWithNoOffest() {
		TextLine aLine = new TextLine("X");
		OffsetLine entry = new OffsetLine(aLine);
		assertSame(aLine, entry.line());
		assertEquals(0, entry.offset());
	}

	@Test
	public void constructorSetsGivenLineAndOffest() {
		TextLine aLine = new TextLine("X");
		OffsetLine entry = new OffsetLine(aLine, 77);
		assertSame(aLine, entry.line());
		assertEquals(77, entry.offset());
	}

	@Test
	public void widthShouldBewidthOfLineIfOffestIsZero() {
		OffsetLine entry = new OffsetLine(new TextLine("1"));
		assertEquals(1, entry.width());
	}
	
	@Test
	public void widthShouldIncludeOffset() {
		OffsetLine entry = new OffsetLine(new TextLine("1"), 33);
		assertEquals(34, entry.width());
	}

	@Test
	public void changeOffsetShouldSetOffsetToGivenValue() {
		OffsetLine entry = new OffsetLine(new TextLine("1"), 33);
		entry.changeOffset(4);
		assertEquals(4, entry.offset());
		
		entry.changeOffset(-1);
		assertEquals(-1, entry.offset());
	}

	@Test
	public void entriesShouldReturnCollectionContainingOnlyItself() {
		OffsetLine entry = new OffsetLine(new TextLine("1"));
		Vector<OffsetLine> theEntries = entry.entries();
		assertEquals(1, theEntries.size());
		assertSame(entry, theEntries.elementAt(0));
	}
}
