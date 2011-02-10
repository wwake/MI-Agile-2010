import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;


public class JustifierTest {

	private Vector<TextSection> inputData;
	
	@Before	
	public void setUp()
	{
		inputData = new Vector<TextSection>();
		inputData.add(new TextLine("Line1"));
		inputData.add(new TextLine("ABC"));
	}
	
	@Test
	public void testConstructorInitializesEmptyResult() {
		Justifier justifier = new Justifier(inputData, Justifier.Arrangement.AllLeft);
		assertNotNull(justifier.resultBlock());
		assertEquals(0, justifier.resultBlock().width());
		assertTrue(justifier.resultBlock().sections().isEmpty());
	}

	@Test
	public void testResetResultCreatesNewEmptyTextBlock() {
		Justifier justifier = new Justifier(inputData, Justifier.Arrangement.AllLeft);
		justifier.execute();
		TextBlock originalResult = justifier.resultBlock();
		assertTrue("Test setup: should have some result", originalResult.width() > 0);
		
		justifier.resetResult();
		assertNotSame(originalResult, justifier.resultBlock());
		assertEquals(0, justifier.resultBlock().width());
		assertTrue(justifier.resultBlock().sections().isEmpty());
	}

	@Test
	public void testSelectedLinesAreTheVectorPassedIntoConstructor() {
		Justifier justifier = new Justifier(inputData, Justifier.Arrangement.AllLeft);
		assertSame(inputData, justifier.selectedLines());
	}

	@Test
	public void testResultBlockContainsAllLinesFromInputDataAfterExecute() {
		Justifier justifier = new Justifier(inputData, Justifier.Arrangement.AllLeft);
		justifier.execute();
		assertEquals(inputData, justifier.resultBlock().sections());
	}

	@Test
	public void testChangeArrangementResetsResult()
	{
		Justifier justifier = new Justifier(inputData, Justifier.Arrangement.AllRight);
		justifier.execute();
		assertFalse("Test setup: make sure something is in result block", justifier.resultBlock().isEmpty());
		
		justifier.changeArrangementTo(Justifier.Arrangement.EndToEnd);
		assertTrue(justifier.resultBlock().isEmpty());
	}

	@Test
	public void testAddLineShouldIncludeGivenLineAtZeroOffsetIfAlignLeft() {
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllLeft);
		TextLine aLine = new TextLine("12345");
		aLine.setOffset(66); // just to make sure it changes
		justifier.addLine(aLine);
		assertTrue(justifier.resultBlock().sections().contains(aLine));
		assertEquals(0, aLine.offset());
	}

	@Test
	public void testAddLineShouldIncludeGivenLineOffsetToAlignRightSidesIfAlignRight() {
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllRight);
		TextLine aLine = new TextLine("12345");
		aLine.setOffset(66); // just to make sure it changes
		justifier.addLine(aLine);
		assertTrue(justifier.resultBlock().sections().contains(aLine));
		assertEquals(0, aLine.offset());
		
		aLine = new TextLine("ABC");
		justifier.addLine(aLine);
		assertTrue(justifier.resultBlock().sections().contains(aLine));
		assertEquals(2, aLine.offset());
	}

	@Test
	public void testAddLineShouldIncludeGivenLineOffsetTheWidthOfBlockIfAlignEndToEnd() 
	{
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.EndToEnd);
		TextLine aLine = new TextLine("12345");
		aLine.setOffset(66); // just to make sure it changes
		justifier.addLine(aLine);
		assertTrue(justifier.resultBlock().sections().contains(aLine));
		assertEquals(0, aLine.offset());
		
		aLine = new TextLine("ABC");
		justifier.addLine(aLine);
		assertTrue(justifier.resultBlock().sections().contains(aLine));
		assertEquals(5, aLine.offset());
	}

	@Test
	public void testAddBlockAddsLinesFromGivenBlockNotTheBlockItself() 
	{
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllLeft);
		TextBlock block = new TextBlock();
		TextLine line1 = new TextLine("ABCDEF");
		TextLine line2 = new TextLine("!@#");
		block.add(line1, 5);
		block.add(line2, 2);
		
		justifier.addBlock(block);
		assertFalse(justifier.resultBlock().sections().contains(block));
		assertTrue(justifier.resultBlock().sections().contains(line1));
		assertTrue(justifier.resultBlock().sections().contains(line2));
	}

	@Test
	public void testAddBlockDoesNotAdjustExistingOffsetsOfBlockLinesRelativeToEachOther() {
		
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllLeft);
		TextBlock block = new TextBlock();
		TextLine line1 = new TextLine("ABCDEF");
		TextLine line2 = new TextLine("!@#");
		block.add(line1, 2);
		block.add(line2, 5);
		justifier.addLine(new TextLine("1234567890"));
	
		justifier.addBlock(block);
		assertEquals(2, justifier.resultBlock().sections().get(1).offset());
		assertEquals(5, justifier.resultBlock().sections().get(2).offset());
		
		justifier.changeArrangementTo(Justifier.Arrangement.AllRight);
		assertTrue("Test setup: result should be empty", justifier.resultBlock().isEmpty());
		justifier.addLine(new TextLine("1234567890"));
		justifier.addBlock(block);
		assertEquals(4, justifier.resultBlock().sections().get(1).offset());
		assertEquals(7, justifier.resultBlock().sections().get(2).offset());
	}

	@Test
	public void testNameResultUsingUsesDefaultNameIfAlignListContainsNoBlocks() 
	{
		Vector<TextSection> theLines = new Vector<TextSection>();
		Justifier justifier = new Justifier(theLines, Justifier.Arrangement.AllLeft);
		justifier.nameResultUsing(theLines, "TheDefault");
		assertEquals("TheDefault", justifier.resultBlock().getName());
		
		TextLine aLine = new TextLine("lkjfdhkdhfsdlkjah");
		aLine.setName("not Jim");
		theLines.add(aLine);
		justifier.nameResultUsing(theLines, "nothing");
		assertEquals("nothing", justifier.resultBlock().getName());
	}
	
	@Test
	public void testNameResultUsingUsesNameFromFirstBlockWithUserGeneratedName()
	{
		Vector<TextSection> data = new Vector<TextSection>();
		TextBlock block = new TextBlock();
		block.setHowCreated(TextLine.CreationType.Imported);
		block.setName("Imported Block");
		data.add(block);
		block = new TextBlock();
		block.setHowCreated(TextLine.CreationType.AppCreated);
		block.setName("App Block");
		data.add(block);
		block = new TextBlock();
		block.setHowCreated(TextLine.CreationType.UserEntered);
		block.setName("User Block");
		data.add(block);
		Justifier justifier = new Justifier(data, Justifier.Arrangement.AllLeft);
		justifier.nameResultUsing(data, "Won't be used");
		assertEquals("User Block", justifier.resultBlock().getName());
	}
	
	@Test
	public void testNameResultUsingUsesNameFromFirstBlockWithAppGeneratedNameIfNoUserGeneratedBlocks()
	{
		Vector<TextSection> data = new Vector<TextSection>();
		TextBlock block = new TextBlock();
		block.setHowCreated(TextLine.CreationType.Imported);
		block.setName("Imported Block");
		data.add(block);
		block = new TextBlock();
		block.setHowCreated(TextLine.CreationType.AppCreated);
		block.setName("App Block");
		data.add(block);
		block = new TextBlock();
		block.setName("Nothing");
		data.add(block);
		Justifier justifier = new Justifier(data, Justifier.Arrangement.AllLeft);
		justifier.nameResultUsing(data, "Won't be used");
		assertEquals("Imported Block", justifier.resultBlock().getName());
	}

	@Test
	public void testAdjustResultSequencesByAppliesGivenOffsetToEachLineInResult()
	{
		Vector<TextSection> data = new Vector<TextSection>();
		TextLine line1 = new TextLine("1234");
		data.add(line1);
		TextLine line2 = new TextLine("ABCD");
		data.add(line2);
		Justifier justifier = new Justifier(data, Justifier.Arrangement.AllLeft);
		justifier.resultBlock().add(line1, 20);
		justifier.resultBlock().add(line2, -10);
		justifier.adjustResultSequencesBy(5);
		assertEquals(25, line1.offset());
		assertEquals(-5, line2.offset());
	}

	@Test
	public void testLeftJustifyAddsLinesFromBlockWithoutAdjustingTheirOffsets() 
	{
		TextBlock block = new TextBlock();
		TextLine line1 = new TextLine("1234");
		block.add(line1, 2);
		TextLine line2 = new TextLine("ABCD");
		block.add(line2, 0);
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllLeft);
		justifier.leftJustify(block);
		assertEquals(2, justifier.resultBlock().sections().get(0).offset());
		assertEquals(0, justifier.resultBlock().sections().get(1).offset());
	}

	@Test
	public void testRightJustifyAddsSameAmountToEachLineSoBlockRightSideAlignsWithResultRight() 
	{
		TextLine longest = new TextLine("1234567890");
		TextBlock block = new TextBlock();
		TextLine line1 = new TextLine("1234");
		block.add(line1, 2);
		TextLine line2 = new TextLine("ABCD");
		block.add(line2, 0);
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllLeft);
		justifier.resultBlock().add(longest, 0);  // Make sure block line offsets get moved
		justifier.rightJustify(block);
		assertEquals(2+4, justifier.resultBlock().sections().get(1).offset());
		assertEquals(0+4, justifier.resultBlock().sections().get(2).offset());
	}
	
	@Test
	public void testRightJustifyChangesOffsetsOfExistingLinesIfBlockLineBeignAddedIsLonger()
	{
		TextBlock block = new TextBlock();
		TextLine longest = new TextLine("1234567890");
		block.add(longest, 0);
		TextLine line1 = new TextLine("1234");
		block.add(line1, 2);
		TextLine existingLine = new TextLine("ABCD");
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllLeft);
		justifier.resultBlock().add(existingLine, 0);  
		justifier.rightJustify(block);
		assertEquals(
				"Original line should now be offset so right side aligns with long line from block", 
				6, 
				justifier.resultBlock().sections().get(0).offset());
		assertEquals("Longest line should be at 0", 0, justifier.resultBlock().sections().get(1).offset());
		assertEquals(
				"Other line in block offset should still be relative to where it was in the block",
				2,
				justifier.resultBlock().sections().get(2).offset());
	}

	@Test
	public void testAppendIncrementsOffsetsByCurrentWidthOfResults() 
	{
		TextLine longest = new TextLine("1234567890");
		TextBlock block = new TextBlock();
		TextLine line1 = new TextLine("1234");
		block.add(line1, 2);
		TextLine line2 = new TextLine("ABCD");
		block.add(line2, 0);
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllLeft);
		justifier.resultBlock().add(longest, 0);  // Make sure block line offsets get moved
		justifier.append(block);
		assertEquals(10+2, justifier.resultBlock().sections().get(1).offset());
		assertEquals(10+0, justifier.resultBlock().sections().get(2).offset());
	}

	@Test
	public void testJoinContentsOfAddsEachLineFromBlockToResultAndChangesTheOffsetByGivenAmount() 
	{
		TextBlock block = new TextBlock();
		TextLine line1 = new TextLine("1234");
		block.add(line1, 2);
		TextLine line2 = new TextLine("ABCD");
		block.add(line2, 0);
		Justifier justifier = new Justifier(new Vector<TextSection>(), Justifier.Arrangement.AllLeft);
		justifier.joinContentsOf(block, 23);
		assertSame(line1, justifier.resultBlock().sections().get(0));
		assertEquals(25, justifier.resultBlock().sections().get(0).offset());
		assertSame(line2, justifier.resultBlock().sections().get(1));
		assertEquals(23, justifier.resultBlock().sections().get(1).offset());
	}
	
	@Test
	public void testExecutePutsValuesFromConstructorIntoResult() 
	{
		Vector<TextSection> data = new Vector<TextSection>();
		TextLine line1 = new TextLine("12345");
		data.add(line1);
		TextLine line2 = new TextLine("ABCD");
		data.add(line2);
		Justifier justifier = new Justifier(data, Justifier.Arrangement.AllRight);
		justifier.execute();
		assertTrue(justifier.resultBlock().sections().contains(line1));
		assertEquals(0, line1.offset());
		assertTrue(justifier.resultBlock().sections().contains(line2));
		assertEquals(1, line2.offset());
	}
	
	@Test
	public void testExecuteSuppliesDefaultNameForResult()
	{
		Vector<TextSection> data = new Vector<TextSection>();
		TextLine line1 = new TextLine("12345");
		data.add(line1);
		Justifier justifier = new Justifier(data, Justifier.Arrangement.AllRight);
		justifier.execute();
		assertTrue(justifier.resultBlock().getName().startsWith(Settings.DefaultNamePrefix));
	}

	class MethodTracingJustifier extends Justifier
	{
		public boolean WasNameResultUsingCalled = false;
		public String SuppliedDefaultName = "";
		public int AddLineCallCount = 0;
		public int AddBlockCallCount = 0;
		
		public MethodTracingJustifier(Vector<TextSection> theLines,	Arrangement howToJoin) 
		{
			super(theLines, howToJoin);
		}

		@Override
		public void nameResultUsing(Vector<TextSection> stuffToAlign, String defaultName)
		{
			WasNameResultUsingCalled = true;
			SuppliedDefaultName = defaultName;
			super.nameResultUsing(stuffToAlign, defaultName);
		}
		
		@Override
		public void addLine(TextLine aLine)
		{
			++AddLineCallCount;
			super.addLine(aLine);
		}
		
		@Override
		public void addBlock(TextBlock aBlock)
		{
			++AddBlockCallCount;
			super.addBlock(aBlock);
		}
	}
	@Test
	public void testJoinAllFromCallsNameResultMethodUsingDefaultNamePassedIn() 
	{
		MethodTracingJustifier justifier = new MethodTracingJustifier(
				new Vector<TextSection>(), 
				Justifier.Arrangement.AllLeft);
		justifier.joinAllFrom(new Vector<TextSection>(), "something");
		assertTrue(justifier.WasNameResultUsingCalled);
		assertEquals("something", justifier.SuppliedDefaultName);
	}
	
	@Test
	public void testJoinAllFromCallsAddLineForEachTextLineInSuppliedData()
	{
		TextLine line1 = new TextLine("1234567890");
		TextLine line2 = new TextLine("ABCD");
		TextBlock block = new TextBlock();
		block.add(new TextLine("1234"), 2);
		block.add(new TextLine("kldjhf"), 0);
		Vector<TextSection> data = new Vector<TextSection>();
		data.add(line1);
		data.add(block);
		data.add(line2);
		MethodTracingJustifier justifier = new MethodTracingJustifier(
				data, 
				Justifier.Arrangement.AllLeft);
		justifier.joinAllFrom(data, "");
		assertEquals(2, justifier.AddLineCallCount);
	}
	
	@Test
	public void testJoinAllFromCallsAddBlockForEachTextBlockInSuppliedData()
	{
		TextBlock block1 = new TextBlock();
		block1.add(new TextLine("1234"), 2);
		block1.add(new TextLine("kldjhf"), 0);
		TextBlock block2 = new TextBlock();
		block2.add(new TextLine("1234"), 2);
		Vector<TextSection> data = new Vector<TextSection>();
		data.add(new TextLine("1234567890"));
		data.add(block1);
		data.add(new TextLine("ABCD"));
		data.add(new TextLine("yet another line"));
		data.add(block2);
		MethodTracingJustifier justifier = new MethodTracingJustifier(
				data, 
				Justifier.Arrangement.AllLeft);
		justifier.joinAllFrom(data, "");
		assertEquals(2, justifier.AddBlockCallCount);
	}
}
