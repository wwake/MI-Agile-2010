import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Vector;

public class EndToEndJustifierTest {

	@Test
	public void addShouldOffsetNewLineTheWidthOfTheCurrentWorkingBlock() {
		EndToEndJustifier justifier = new EndToEndJustifier(new Vector<TextSection>());
		int currentWidth = justifier.workingBlock().width();
		justifier.add(new TextLine("abc"));
		BlockEntry entry = justifier.workingBlock().entries().elementAt(0);
		assertEquals(currentWidth, entry.offset());
		
		currentWidth = justifier.workingBlock().width();
		assertEquals("Test setup: just making sure", 3, currentWidth);
		justifier.add(new TextLine("bunch-o-text"));
		entry = justifier.workingBlock().entries().elementAt(0);
		assertEquals("Existing line offset should not have changed", 0, entry.offset());
		entry = justifier.workingBlock().entries().elementAt(1);
		assertEquals(currentWidth, entry.offset());
	}

	@Test
	public void addLinesFromShouldKeepCurrentOffsetsOfEachLineInAddedBlockIfAddedFirst() {
		TextBlock block = new TextBlock();
		block.add(new TextLine("(*^&%"), 256);
		block.add(new TextLine("whatever"), 3876);
		
		EndToEndJustifier justifier = new EndToEndJustifier(new Vector<TextSection>());
		justifier.addLinesFrom(block);
		BlockEntry entry = justifier.workingBlock().entries().elementAt(0);
		assertEquals(256, entry.offset());
		entry = justifier.workingBlock().entries().elementAt(1);
		assertEquals(3876, entry.offset());
	}

	@Test
	public void addLinesFromShouldApplyCurrentWidthToOffsetsOfEachLineInAddedBlock() {
		TextBlock block = new TextBlock();
		block.add(new TextLine("(*^&%"), 4);
		block.add(new TextLine("whatever"), 39);
		
		EndToEndJustifier justifier = new EndToEndJustifier(new Vector<TextSection>());
		justifier.add(new TextLine("1234567890"));
		
		justifier.addLinesFrom(block);
		BlockEntry entry = justifier.workingBlock().entries().elementAt(0);
		assertEquals("Existing line offset should not have changed", 0, entry.offset());
		entry = justifier.workingBlock().entries().elementAt(1);
		assertEquals(14, entry.offset());
		entry = justifier.workingBlock().entries().elementAt(2);
		assertEquals(49, entry.offset());
	}

}
