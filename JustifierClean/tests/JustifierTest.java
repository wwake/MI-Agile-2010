import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;


public class JustifierTest {

	public class CallTrackingJustifier extends Justifier
	{
		public int addCalledCount = 0;
		public int addLinesFromCalledCount = 0;
		
		public CallTrackingJustifier(Vector<TextSection> theLines) {
			super(theLines);
		}

		@Override
		public void add(TextLine aLine) { ++addCalledCount; }

		@Override
		public void addLinesFrom(TextBlock aBlock) { ++addLinesFromCalledCount; }
	}
	
	@Test
	public void testResultBlockShouldNeverBeNull()
	{
		CallTrackingJustifier justifier = new CallTrackingJustifier(this.testData());
		assertNotNull(justifier.resultBlock());
		assertTrue("ResultBlock should be empty by default", justifier.resultBlock().isEmpty());
	}
	
	@Test
	public void testNewResultCreatesNewBlockEveryTime() {
		
		Justifier justifier = new CallTrackingJustifier(new Vector<TextSection>());
		TextBlock block1 = justifier.newResult();
		TextBlock block2 = justifier.newResult();
		assertFalse(block1 == block2);
	}

	@Test
	public void testNewResultCallsAddForEveryTopLevelLineInInputList()
	{
		CallTrackingJustifier justifier = new CallTrackingJustifier(this.testData());
		justifier.newResult();
		assertEquals(3, justifier.addCalledCount);
	}

	@Test
	public void testNewResultCallsAddLinesFromForEveryBlockInInputList()
	{
		CallTrackingJustifier justifier = new CallTrackingJustifier(this.testData());
		justifier.newResult();
		assertEquals(2, justifier.addLinesFromCalledCount);
	}
	
	private Vector<TextSection> testData()
	{
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

/* I wouldn't bother with that first assertNotNull - doesn't move the action forward, and if it's null, the next line 
will fail.
I wonder if this can all be simplified by unifying the interfaces.
*/