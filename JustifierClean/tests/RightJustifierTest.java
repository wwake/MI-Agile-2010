import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Vector;


public class RightJustifierTest {

	@Test
	public void addPutsLineInBlockWithZeroOffsetIfFirstLineAdded() {
		RightJustifier justifier = new RightJustifier(new Vector<TextSection>());
		TextLine addedLine = new TextLine("ABCD");
		justifier.add(addedLine);
		BlockEntry entry = justifier.workingBlock().entries().get(0);
		assertSame(addedLine, entry.line());
		assertEquals(0, entry.offset());
	}

	@Test
	public void testAddLinesFromShouldUseExistingOffsetsIfBlockIsFirstItemAdded() {
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
