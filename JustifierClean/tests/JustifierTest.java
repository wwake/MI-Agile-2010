import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Test;


public class JustifierTest {

	public class CallTrackingJustifier extends Justifier
	{
		public int addSectionCalledCount = 0;
		
		public CallTrackingJustifier(Vector<TextSection> theLines) {
			super(theLines);
		}

		@Override
		public void addSection(TextSection section) { ++addSectionCalledCount;}
	}
	
	@Test
	public void workingBlockShouldBeInitializedEmpty() {
		CallTrackingJustifier justifier = new CallTrackingJustifier(this.testData());
		assertTrue("ResultBlock should be empty by default", justifier.workingBlock().isEmpty());
	}
	
	@Test
	public void addToResultPutsGivenLineInResultBlock() {
		Justifier justifier = new CallTrackingJustifier(new Vector<TextSection>());
		TextLine line = new TextLine("OIU");
		justifier.addToResult(line, 3);
		OffsetLine entry = justifier.workingBlock().entries().elementAt(0);
		assertSame(line, entry.line());
		assertEquals(3, entry.offset());
	}
	
	@Test
	public void newResultCreatesNewBlockEveryTime() {
		Justifier justifier = new CallTrackingJustifier(new Vector<TextSection>());
		TextBlock block1 = justifier.newResult();
		TextBlock block2 = justifier.newResult();
		assertFalse(block1 == block2);
	}

	@Test
	public void newResultCallsAddForEveryEntryInAllSections() {
		CallTrackingJustifier justifier = new CallTrackingJustifier(this.testData());
		justifier.newResult();
		assertEquals(5, justifier.addSectionCalledCount);
	}
	
	private Vector<TextSection> testData() {
		Vector<TextSection> data = new Vector<TextSection>();
		data.add(new TextLine("123"));
		TextBlock textBlock = new TextBlock();
		textBlock.add(new TextLine("Embedded in block 1. Should not call add"), 0);
		data.add(textBlock);
		data.add(new TextLine("ABCDE"));
		textBlock = new TextBlock(SectionName.userSuppliedNameFrom("Fred"));
		textBlock.add(new TextLine("Embedded in block 2. Should not call add either"), 0);
		data.add(textBlock);
		data.add(new TextLine("word"));
		return data;
	}
}