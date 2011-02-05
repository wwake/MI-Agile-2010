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
	public void testExecute() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinAllFrom() {
		fail("Not yet implemented");
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
	public void testAddLineShouldIncludeGivenLineOffsetTheWidthOfBlockIfAlignEndToEnd() {
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
	public void testAddBlockAddsLinesFromGivenBlockNotTheBlockItself() {
		
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
	public void testNameResultUsing() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdjustResultSequencesBy() {
		fail("Not yet implemented");
	}

	@Test
	public void testLeftJustify() {
		fail("Not yet implemented");
	}

	@Test
	public void testRightJustify() {
		fail("Not yet implemented");
	}

	@Test
	public void testSpread() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinContentsOf() {
		fail("Not yet implemented");
	}

}
