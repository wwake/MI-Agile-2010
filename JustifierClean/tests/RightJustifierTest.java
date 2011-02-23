import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Vector;


public class RightJustifierTest {

	@Test
	public void addShouldPutLineInBlockWithZeroOffsetIfFirstLineAdded() {
		RightJustifier justifier = new RightJustifier(new Vector<TextSection>());
		TextLine addedLine = new TextLine("ABCD");
		justifier.add(addedLine);
		BlockEntry entry = justifier.workingBlock().entries().get(0);
		assertSame(addedLine, entry.line());
		assertEquals(0, entry.offset());
	}
	
	@Test
	public void addShouldAdjustOffsetOfGivenLineIfItIsShorter() {
		RightJustifier justifier = new RightJustifier(new Vector<TextSection>());
		TextLine longerLine = new TextLine("ABCD");
		justifier.add(longerLine);
		
		TextLine shorterLine = new TextLine("12");
		justifier.add(shorterLine);
		BlockEntry entry = justifier.workingBlock().entries().get(0);
		assertSame(longerLine, entry.line());
		assertEquals("Should not have touched offset of existing line", 0, entry.offset());
		entry = justifier.workingBlock().entries().get(1);
		assertSame(shorterLine, entry.line());
		assertEquals("Should have determined offset for shorter line", 2, entry.offset());
	}
	
	@Test
	public void addShouldAdjustOffsetsOfAnyExistingLinesIfGivenLineIsWiderThanWorkingBlockWidth() {
		RightJustifier justifier = new RightJustifier(new Vector<TextSection>());
		justifier.add(new TextLine("1234"));
		justifier.add(new TextLine("12"));
		
		TextLine longer = new TextLine("1234567890");
		justifier.add(longer);
		BlockEntry entry = justifier.workingBlock().entries().get(0);
		assertEquals("Should have moved first line offset", 6, entry.offset());
		entry = justifier.workingBlock().entries().get(1);
		assertEquals("Should have moved second line offset", 8, entry.offset());
		entry = justifier.workingBlock().entries().get(2);
		assertEquals("New longest line should have no offset", 0, entry.offset());
	}

	@Test
	public void addLinesFromShouldUseExistingOffsetsIfBlockIsFirstItemAdded() {
		RightJustifier justifier = new RightJustifier(new Vector<TextSection>());
		TextBlock block = new TextBlock();
		TextLine line1 = new TextLine("line one offset 5");
		TextLine line2 = new TextLine("line 2 offset 0");
		TextLine line3 = new TextLine("loner line 3 offset just 2");
		block.add(line1, 5);
		block.add(line2, 0);
		block.add(line3, 2);
		
		justifier.addLinesFrom(block);
		assertEquals("Each line from given block should have been added to result", 3, justifier.workingBlock().entries().size());
		BlockEntry entry = justifier.workingBlock().entries().get(0);
		assertSame(line1, entry.line());
		assertEquals(5, entry.offset());

		entry = justifier.workingBlock().entries().get(1);
		assertSame(line2, entry.line());
		assertEquals(0, entry.offset());

		entry = justifier.workingBlock().entries().get(2);
		assertSame(line3, entry.line());
		assertEquals(2, entry.offset());
	}
}
