import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;



public class TextBlockTest {

	@Test
	public void testConstructorInitializesValues() {
		
		TextBlock block = new TextBlock();
		assertTrue("Should initialize an empty sections list", block.isEmpty());
		assertEquals(
				"Default creations type to Application", 
				TextBlock.NameSource.APPLICATION, 
				block.creationType());
		assertEquals("name should be empty by default", "", block.name());
	}
	
	@Test
	public void testIsEmptyIsTrueIfItContainsNoLines()
	{
		assertTrue(new TextBlock().isEmpty());
	}
	
	@Test
	public void testIsEmptyIsFalseIfItContainsAnyLines()
	{
		TextBlock block = new TextBlock();
		block.add(new TextLine(""), 0); // there doesn't need to be anything in the line
		assertFalse(block.isEmpty());
	}

	@Test
	public void testAdd() {
		TextBlock block = new TextBlock();
		TextLine aLine = new TextLine("whatever");
		block.add(aLine, 0);
		assertEquals(1, block.sections().size());
		Vector<TextSection> sectionList = new Vector<TextSection>(block.sections());
		assertEquals(
				"line value should be the same", 
				aLine.toString(), 
				sectionList.firstElement().toString());

		block.add(new TextLine("other"), 0);
		assertEquals(2, block.sections().size());
	}
	
	@Test
	public void testAddUpdatesOffsetOnAddedLine() {
		TextBlock block = new TextBlock();
		TextLine aLine = new TextLine("whatever");
		block.add(aLine, 33);
		assertEquals(1, block.sections().size());

		//TODO: add offsetOf() method to block
//		assertEquals(33, sectionList.firstElement().offset());
//		assertEquals("original line offest should not have changed", 0, aLine.offset());
	}

	@Test
	public void testWidthIsZeroIfBlockIsEmpty() {
		
		assertEquals(0, new TextBlock().width());
	}
	
	@Test
	public void testWidthEqualsTheWidthOfContainedTextIfOnlySingleLine()
	{
		TextLine line = new TextLine("12345");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		assertEquals(5, block.width());
	}
	
	@Test
	public void testWidthIncorporatesOffsetsInDeterminingValue()
	{
		TextLine line = new TextLine("12345");
		TextLine lineOffsetRight = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		block.add(lineOffsetRight, 100);
		assertEquals(103, block.width());
	}
	
	@Test
	public void testWidthAccountsForNegativeOffsets()
	{
		TextLine line = new TextLine("1");
		TextLine lineOffsetNegative = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		block.add(lineOffsetNegative, -3);
		assertEquals(4, block.width());
	}
	
	@Test
	public void testWidthStartsAtZeroIfAllContainedLinesHavePositiveOffsets()
	{
		TextLine line1 = new TextLine("1");
		TextLine line2 = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line1, 5);
		block.add(line2, 4);
		assertEquals(7, block.width());
	}

	@Test
	public void testIsNameUserCreatedIsTrueOnlyForUserEnteredCreationType() {
		TextBlock block = new TextBlock();
		block.setHowCreated(TextBlock.NameSource.IMPORTED);
		assertFalse(block.isNameUserCreated());
		
		block.setHowCreated(TextBlock.NameSource.APPLICATION);
		assertFalse(block.isNameUserCreated());
		
		block.setHowCreated(TextBlock.NameSource.USER);
		assertTrue(block.isNameUserCreated());
	}

	@Test
	public void testRemoveDoesNothingIfGivenLineIsNotThere() {
		TextBlock block = new TextBlock();
		TextLine aLine = new TextLine("whatever");
		TextLine notThere = new TextLine("some other line");
		block.add(aLine, 0);
		block.remove(notThere);
		assertEquals(1, block.sections().size());
	}

	@Test
	public void testRemoveDeletesLineFromCollection() {
		TextBlock block = new TextBlock();
		TextLine aLine = new TextLine("whatever");
		TextLine anotherLine = new TextLine("some other line");
		block.add(aLine, 0);
		block.add(anotherLine, 5);
		block.remove(aLine);
		assertEquals(1, block.sections().size());
		Vector<TextSection> sectionList = new Vector<TextSection>(block.sections());
		assertEquals(anotherLine, sectionList.firstElement());
	}
}
