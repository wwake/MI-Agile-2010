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
		
		justifier.resetResultDocument();
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
	public void testExecute() {
		fail("Not yet implemented");
	}

	@Test
	public void testJoinAllFrom() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLine() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddBlock() {
		fail("Not yet implemented");
	}

	@Test
	public void testNameResultDocumentUsing() {
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
