import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TextBlockTest {

	@Test
	public void defaultConstructorInitializesValues() {
		TextBlock block = new TextBlock();
		assertTrue("Should initialize an empty sections list", block.isEmpty());
		assertTrue(
				"name should be default system name", 
				block.name().toString().startsWith(SectionName.DefaultNamePrefix));
		assertTrue("name should be app generated", block.name().isApplicationGenerated());
	}
	
	@Test
	public void constructorWithNameSetsGivenName() {
		SectionName blockName = SectionName.importedNameFrom("import");
		TextBlock block = new TextBlock(blockName);
		assertTrue("Should initialize an empty sections list", block.isEmpty());
		assertSame(blockName, block.name());
	}
	
	@Test
	public void isEmptyIsTrueIfItContainsNoLines() {
		assertTrue(new TextBlock().isEmpty());
	}
	
	@Test
	public void isEmptyIsFalseIfItContainsAnyLines() {
		TextBlock block = new TextBlock();
		block.add(new TextLine(""), 0); // there doesn't need to be anything in the line
		assertFalse(block.isEmpty());
	}

	@Test
	public void addAppendsGivenLineToBlock() {
		TextBlock block = new TextBlock();
		TextLine aLine = new TextLine("whatever");
		block.add(aLine, 0);
		assertEquals(1, block.entries().size());
		assertEquals(
				"line value should be the same", 
				aLine.toString(), 
				block.entries().firstElement().line().toString());

		block.add(new TextLine("other"), 0);
		assertEquals(2, block.entries().size());
	}
	
	@Test
	public void addUpdatesOffsetOnAddedLine() {
		TextBlock block = new TextBlock();
		TextLine aLine = new TextLine("whatever");
		block.add(aLine, 33);
		assertEquals(1, block.entries().size());
		assertEquals(33, block.entries().firstElement().offset());
	}

	@Test
	public void widthIsZeroIfBlockIsEmpty() {
		assertEquals(0, new TextBlock().width());
	}
	
	@Test
	public void widthEqualsTheWidthOfContainedTextIfOnlySingleLine() {
		TextLine line = new TextLine("12345");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		assertEquals(5, block.width());
	}
	
	@Test
	public void widthIncorporatesOffsetsInDeterminingValue() {
		TextLine line = new TextLine("12345");
		TextLine lineOffsetRight = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		block.add(lineOffsetRight, 100);
		assertEquals(103, block.width());
	}
	
	@Test
	public void widthAccountsForNegativeOffsets() {
		TextLine line = new TextLine("1");
		TextLine lineOffsetNegative = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line, 0);
		block.add(lineOffsetNegative, -3);
		assertEquals(4, block.width());
	}
	
	@Test
	public void widthStartsAtZeroIfAllContainedLinesHavePositiveOffsets() {
		TextLine line1 = new TextLine("1");
		TextLine line2 = new TextLine("789");
		TextBlock block = new TextBlock();
		block.add(line1, 5);
		block.add(line2, 4);
		assertEquals(7, block.width());
	}
	
	@Test
	public void adjustAllOffsetsByShouldApplyGivenValueToAllLines()
	{
		TextLine line1 = new TextLine("Line 1");
		TextLine line2 = new TextLine("AnotherLine");
		TextLine line3 = new TextLine("XYZ");
		TextBlock block = new TextBlock();
		block.add(line1, -2);
		block.add(line2, 22);
		block.add(line3, 0);
		block.adjustAllOffsetsBy(7);
		
		BlockEntry entry = block.entries().elementAt(0);
		assertEquals(5, entry.offset());
		entry = block.entries().elementAt(1);
		assertEquals(29, entry.offset());
		entry = block.entries().elementAt(2);
		assertEquals(7, entry.offset());
	}
}
